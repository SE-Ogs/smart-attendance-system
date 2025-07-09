package com.attendance.attendance.session.Controller;
import com.attendance.attendance.session.model.*;
import java.nio.file.attribute.UserPrincipal;
import java.util.List;

import javax.management.Notification;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public class NotificationService {

    @RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
    
    // @GetMapping
    // public ResponseEntity<List<Notification>> getNotifications(@AuthenticationPrincipal UserPrincipal user) {
        // Long studentId = user.getSessionId();
        // List<Notification> codes = notificationService.fetchAndClear(studentId);
        // return ResponseEntity.ok(codes);
    // }
}
}
