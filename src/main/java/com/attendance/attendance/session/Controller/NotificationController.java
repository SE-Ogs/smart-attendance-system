package com.attendance.attendance.session.Controller; // Assuming this package

import com.attendance.attendance.session.model.Notification;
import com.attendance.attendance.session.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    public ResponseEntity<List<Notification>> getNotifications(@AuthenticationPrincipal Principal user) {
        // Assuming you have a way to extract student ID from the principal
        Long studentId = extractStudentId(user);
        List<Notification> notifications = notificationService.fetchAndClear(studentId);
        return ResponseEntity.ok(notifications);
    }

    private Long extractStudentId(Principal user) {
        // Implement this method based on your authentication system
        // This is a placeholder implementation
        return 1L; // Replace with actual logic to extract student ID from 'user'
    }
}