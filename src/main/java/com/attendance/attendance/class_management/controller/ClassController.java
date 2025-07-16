package com.attendance.attendance.class_management.controller;

import java.util.List;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.attendance.attendance.class_management.dto.ClassRequest;
import com.attendance.attendance.class_management.service.ClassService;
import com.attendance.entities.ClassEntity;
import com.attendance.entities.User;

@RestController
@RequestMapping("/api/classes")
public class ClassController {

    @Autowired
    private ClassService classService;

    //teacher creating a class
    @PostMapping
    public ResponseEntity<?> createClass(@RequestBody ClassRequest request, Authentication auth) {
        try {
            User user = getUserFromAuth(auth);
            if (user == null || user.getRole() != User.Role.TEACHER) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Only teachers can create classes.");
            }
            ClassEntity savedClass = classService.createClass(request, user);
            return ResponseEntity.ok(savedClass);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Error creating class: " + e.getMessage());
        }
    }

    //teacher viewing the class
    @GetMapping
    public ResponseEntity<?> getClasses(Authentication auth){
        try{
            User user = getUserFromAuth(auth);
            if (user == null || user.getRole() != User.Role.TEACHER) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Only teachers can view classes.");
            }
            List<ClassEntity> classes = classService.getClassesbyTeacher(user);
            return ResponseEntity.ok(classes);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Error fetching classes: " + e.getMessage());
        }
    }

    private User getUserFromAuth(Authentication auth) {
        if (auth == null) return null;
        // For demo: assume auth is a custom Authentication with getName()
        String username = null;
        try {
            username = (String) auth.getClass().getMethod("getName").invoke(auth);
        } catch (Exception e) {
            return null;
        }
        User user = new User();
        // user.setUsername(username);
        // if ("teacher".equalsIgnoreCase(username)) {
        //     user.setId(1L);
        //     user.setRole(User.Role.TEACHER);
        // } else if ("student".equalsIgnoreCase(username)) {
        //     user.setId(2L);
        //     user.setRole(User.Role.STUDENT);
        // }
        return user;
    }
}

