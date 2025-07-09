package com.attendance.attendance.session.service;

import com.attendance.attendance.session.model.Notification;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class NotificationService {
    // studentId -> list of pending notifications
    private final ConcurrentMap<Long, List<Notification>> notifications = new ConcurrentHashMap<>();

    /**
     * Sends a new attendance code notification to a specific student.
     * The notification is stored in an in-memory list for the student.
     * @param studentId The ID of the student to notify.
     * @param code The 6-digit attendance code.
     * @param sessionId The ID of the session the code belongs to.
     */
    public void sendCode(Long studentId, String code, Long sessionId) {
        // ComputeIfAbsent ensures a list exists for the studentId, then adds the notification
        notifications
            .computeIfAbsent(studentId, id -> new CopyOnWriteArrayList<>())
            .add(new Notification(sessionId, code, Instant.now())); // Automatically set timestamp
    }

    /**
     * Fetches all pending notifications for a student and clears them from memory.
     * This simulates the student having received and processed the notifications.
     * @param studentId The ID of the student whose notifications are to be fetched and cleared.
     * @return A list of notifications for the student, or an empty list if none were found.
     */
    public List<Notification> fetchAndClear(Long studentId) {
        // Remove the list of notifications for the studentId, effectively clearing them
        List<Notification> list = notifications.remove(studentId);
        return list != null ? list : Collections.emptyList();
    }
}