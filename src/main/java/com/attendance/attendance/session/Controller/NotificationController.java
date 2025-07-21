package com.attendance.attendance.session.Controller;

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
        Long studentId = extractStudentId(user);

        if (studentId == null) {
            // 401 Unauthorized is more appropriate if the user can't be identified
            return ResponseEntity.status(401).build();
        }

        List<Notification> notifications = notificationService.fetchAndClear(studentId);
        return ResponseEntity.ok(notifications);
    }

    /**
     * Extracts the student ID based on the agreement that principal.getName()
     * will return the student's ID as a String.
     */
    private Long extractStudentId(Principal user) {
        if (user == null || user.getName() == null) {
            System.err.println("Cannot extract student ID, user principal is null or has no name.");
            return null;
        }

        try {
            // This is the "contract". Your code trusts that the other developer
            // has made sure user.getName() is the ID.
            return Long.parseLong(user.getName());
        } catch (NumberFormatException e) {
            // This error will appear if the other person doesn't follow the agreement.
            System.err.println("Error: The principal name '" + user.getName() + "' is not a valid student ID.");
            return null;
        }
    }
}