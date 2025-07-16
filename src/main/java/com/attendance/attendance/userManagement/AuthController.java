package com.attendance.attendance.userManagement;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    //@PostMapping("/register")
    //public ResponseEntity<?> register(@RequestBody User user) {

        // Step 1: Check if the username is already taken
        // This prevents duplicate accounts using the same username

        // Step 2: Validate the user's role
        // Ensure the role is either STUDENT or TEACHER
        // This will dictatate the features accessable by that account

        // Step 3: Hash the user's password
        // Use a secure hashing algorithm before saving to the database
        // This is important for protecting user credentials

        // Step 4: Save the user to the database
        // Store the user details (username, password, full name, role, etc.)

        // Step 5: Return a success response
        // return a message like "User registered successfully"

        //return ResponseEntity.ok("User registered successfully");
   // }
}

