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

    /**
     * Retrieves all pending notifications for the authenticated student and clears them from memory.
     * @param user The authenticated principal representing the current user (student).
     * @return A ResponseEntity containing a list of Notification objects.
     */
    @GetMapping
    public ResponseEntity<List<Notification>> getNotifications(@AuthenticationPrincipal Principal user) {        
        Long studentId = extractStudentId(user);

        
        if (studentId == null) {            
            return ResponseEntity.badRequest().build();
        }

        List<Notification> notifications = notificationService.fetchAndClear(studentId);
        return ResponseEntity.ok(notifications);
    }

    /**
     * Extracts the student ID from the authenticated Principal object.
     * This method needs to be implemented based on your specific Spring Security setup
     * and how user details (including the student ID) are stored in the Principal.
     *
     * IMPORTANT: This is a placeholder. You must replace 'YourCustomUserDetails'
     * and the logic within to match your actual Spring Security implementation.
     *
     * @param user The authenticated Principal.
     * @return The student's ID, or null if it cannot be extracted.
     */
    private Long extractStudentId(Principal user) {
        
        
        
        if (user instanceof org.springframework.security.authentication.UsernamePasswordAuthenticationToken) {
            Object principalObject = ((org.springframework.security.authentication.UsernamePasswordAuthenticationToken) user).getPrincipal();

            
            
            
            

            
            try {
                return Long.parseLong(user.getName()); 
            } catch (NumberFormatException e) {
                
                System.err.println("Error extracting student ID: " + e.getMessage());
                return null;
            }
        }
        
        

        
        System.err.println("Could not extract student ID from principal type: " + user.getClass().getName());
        return null; 
        
    }
}