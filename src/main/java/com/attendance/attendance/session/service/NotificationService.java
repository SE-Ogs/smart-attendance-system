package com.attendance.attendance.session.service;

import com.attendance.attendance.session.model.Notification;
import org.springframework.messaging.simp.SimpMessagingTemplate; // NEW Import
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class NotificationService {
    // studentId -> list of pending notifications (fallback/temporary storage)
    private final ConcurrentMap<Long, List<Notification>> notifications = new ConcurrentHashMap<>();

    private final SimpMessagingTemplate messagingTemplate; // NEW: For sending WebSocket messages

    public NotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    /**
     * Sends a new attendance code notification to a specific student.
     * This method attempts to push the notification via WebSocket first.
     * If the student is not connected, it falls back to storing in an in-memory list.
     * @param studentId The ID of the student to notify.
     * @param code The 6-digit attendance code.
     * @param sessionId The ID of the session the code belongs to.
     */
    public void sendCode(Long studentId, String code, Long sessionId) {
        Notification newNotification = new Notification(sessionId, code, Instant.now());

        // Attempt to send via WebSocket directly to the user's private queue
        try {
            // The destination for a specific user's private queue would typically be:
            // /user/{studentId}/queue/notifications
            // Spring handles mapping /user/{studentId} to the correct session
            messagingTemplate.convertAndSendToUser(
                String.valueOf(studentId), // The user identifier (needs to match what Spring Security uses)
                "/queue/notifications",    // The private queue destination
                newNotification
            );
            System.out.println("Pushed notification to student " + studentId + " via WebSocket: " + newNotification);
        } catch (Exception e) {
            // If WebSocket push fails (e.g., student not connected), fallback to in-memory list
            System.err.println("Failed to push notification to student " + studentId + " via WebSocket. Falling back to in-memory storage. Error: " + e.getMessage());
            notifications
                .computeIfAbsent(studentId, id -> new CopyOnWriteArrayList<>())
                .add(newNotification);
        }
    }

    /**
     * Fetches all pending notifications for a student from the in-memory list and clears them.
     * This method serves as a fallback for students who might not be using WebSockets,
     * or to retrieve notifications that were stored when a WebSocket connection wasn't active.
     * @param studentId The ID of the student whose notifications are to be fetched and cleared.
     * @return A list of notifications for the student, or an empty list if none were found.
     */
    public List<Notification> fetchAndClear(Long studentId) {
        List<Notification> list = notifications.remove(studentId);
        return list != null ? list : Collections.emptyList();
    }
}