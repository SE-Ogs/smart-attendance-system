package com.attendance.attendance.session.Controller;

import com.attendance.attendance.session.model.Notification;
import com.attendance.attendance.session.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken; // Added for extractStudentId example
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
     * Retrieves all pending notifications for the authenticated student from the in-memory list
     * and clears them from memory. This acts as a fallback for WebSocket push.
     * @param user The authenticated principal representing the current user (student).
     * @return A ResponseEntity containing a list of Notification objects.
     */
    @GetMapping
    public ResponseEntity<List<Notification>> getNotifications(@AuthenticationPrincipal Principal user) {
        // Extract student ID from the authenticated principal
        Long studentId = extractStudentId(user);

        if (studentId == null) {
            return ResponseEntity.badRequest().build(); // Or 401 Unauthorized
        }

        List<Notification> notifications = notificationService.fetchAndClear(studentId);
        return ResponseEntity.ok(notifications);
    }

    /**
     * Extracts the student ID from the authenticated Principal object.
     * This method needs to be implemented based on your specific Spring Security setup
     * and how user details (including the student ID) are stored in the Principal.
     *
     * IMPORTANT: This is a placeholder. 
     *
     * @param user The authenticated Principal.
     * @return The student's ID, or null if it cannot be extracted.
     */
    private Long extractStudentId(Principal user) {
        // --- START: CUSTOM IMPLEMENTATION REQUIRED HERE ---
        // This is a common pattern for Spring Security setups where you have a custom UserDetails service.
        // Assuming your 'Principal' object wraps a UserDetails implementation that contains studentId.
        if (user instanceof UsernamePasswordAuthenticationToken) {
            Object principalObject = ((UsernamePasswordAuthenticationToken) user).getPrincipal();

            // Example 1: If your custom UserDetails class has a getStudentId() method
            // This is the most robust way if you've implemented CustomUserDetails
            // if (principalObject instanceof YourCustomUserDetails) { // Replace YourCustomUserDetails with your actual class
            //     return ((YourCustomUserDetails) principalObject).getStudentId();
            // }

            // Example 2: If the Principal's name (user.getName()) is directly the student ID
            // Use with caution, as user.getName() is often a username/email, not an ID.
            try {
                return Long.parseLong(user.getName());
            } catch (NumberFormatException e) {
                System.err.println("Error parsing student ID from principal name: " + user.getName() + " - " + e.getMessage());
                return null;
            }
        }
        // Example 3: If using OAuth2 (e.g., Google Login)
        // if (user instanceof org.springframework.security.oauth2.core.user.OAuth2User) {
        //     org.springframework.security.oauth2.core.user.OAuth2User oauth2User = (org.springframework.security.oauth2.core.user.OAuth2User) user;
        //     // The key for the student ID depends on your OAuth2 provider and how you map attributes.
        //     Object idAttribute = oauth2User.getAttributes().get("sub"); // Common for OIDC
        //     if (idAttribute instanceof String) {
        //         try {
        //             return Long.parseLong((String) idAttribute);
        //         } catch (NumberFormatException e) { /* handle */ }
        //     } else if (idAttribute instanceof Number) {
        //         return ((Number) idAttribute).longValue();
        //     }
        // }

        // Fallback for unhandled principal types or if ID extraction fails.
        System.err.println("Could not extract student ID from principal type: " + (user != null ? user.getClass().getName() : "null"));
        return null; // Or throw an IllegalArgumentException
        // --- END: CUSTOM IMPLEMENTATION REQUIRED HERE ---
    }
}