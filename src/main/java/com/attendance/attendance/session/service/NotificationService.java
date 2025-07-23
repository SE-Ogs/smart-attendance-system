package com.attendance.attendance.session.service;

import com.attendance.attendance.session.model.Notification;
import com.attendance.attendance.repository.NotificationRepository;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class NotificationService {
    
    private final SimpMessagingTemplate messagingTemplate;
    private final NotificationRepository notificationRepository;

    public NotificationService(SimpMessagingTemplate messagingTemplate, NotificationRepository notificationRepository) {
        this.messagingTemplate = messagingTemplate;
        this.notificationRepository = notificationRepository;
    }

    public void sendCode(Long studentId, String code, Long sessionId) {
        // --- FIX IS HERE ---
        // Added the 'studentId' to the constructor call to match the model.
        Notification newNotification = new Notification(sessionId, code, Instant.now(), studentId);

        try {
            messagingTemplate.convertAndSendToUser(
                String.valueOf(studentId),
                "/queue/notifications",
                newNotification
            );
            System.out.println("Pushed notification to student " + studentId + " via WebSocket: " + newNotification);
        } catch (Exception e) {
            System.err.println("Failed to push notification to student " + studentId + " via WebSocket. Storing in database. Error: " + e.getMessage());
            // When saving, the notification now correctly includes the studentId
            notificationRepository.save(newNotification);
        }
    }

    public List<Notification> fetchAndClear(Long studentId) {
        List<Notification> notifications = notificationRepository.findByStudentId(studentId);
        if (notifications != null && !notifications.isEmpty()) {
            notificationRepository.deleteAll(notifications);
        }
        return notifications;
    }
}
