package com.attendance.attendance.class_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.attendance.attendance.class_management.Requests.ClassRequest;
import com.attendance.attendance.class_management.service.ClassService;
import com.attendance.attendance.entities.User;
import com.attendance.attendance.entities.User.Role;
// import com.attendance.attendance.service.UserService; 

@RestController
@RequestMapping("/api/classes")
public class ClassController {

    @Autowired
    private ClassService classService;

    //@Autowired
    // private UserService userService; // Service to fetch user details

    // // Helper method to extract the user from the Authentication object
    // private User getUserFromAuth(String authToken) {
    //     // Use the UserService to fetch the user based on the token
    //     // User user = userService.getUserByAuthToken(authToken);
    //     // if (user == null) {
    //     //     throw new IllegalArgumentException("Invalid authentication token");
    //     // }
    //     return null;
    // }

    // Teacher creating a class
    // @PostMapping
    // public ResponseEntity<?> createClass(@RequestBody ClassRequest request, String authToken) {
    //     try {
    //         User user = getUserFromAuth(authToken);

    //         // Check if the user is a teacher
    //         if (user.getRole() != Role.TEACHER) {
    //             return ResponseEntity.status(HttpStatus.FORBIDDEN)
    //                     .body("Only teachers are allowed to create classes.");
    //         }

    //         // Proceed with class creation
    //         classService.createClass(request, user);
    //         return ResponseEntity.status(HttpStatus.CREATED).body("Class created successfully.");
    //     } catch (Exception e) {
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    //                 .body("Error creating class: " + e.getMessage());
    //     }
    // }

    // Only teachers can view classes
    // @GetMapping
    // public ResponseEntity<?> getClasses(String authToken) {
    //     try {
    //         User user = getUserFromAuth(authToken);

    //         // Check if the user is a teacher
    //         if (user.getRole() != Role.TEACHER) {
    //             return ResponseEntity.status(HttpStatus.FORBIDDEN)
    //                     .body("Only teachers are allowed to view classes.");
    //         }

    //         // Fetch classes for the teacher
    //         return ResponseEntity.ok(classService.getClassesByTeacher(user));
    //     } catch (Exception e) {
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST)
    //                 .body("Error fetching classes: " + e.getMessage());
    //     }
    // }
}