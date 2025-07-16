package com.attendance.attendance.classManagement.controller;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.attendance.attendance.classManagement.Requests.AddStudentRequest;
import com.attendance.attendance.classManagement.Response.StudentResponse;
import com.attendance.attendance.classManagement.Service.studentService;

@RestController
@RequestMapping("/api/classes/{classId}/students")
public class StudentController {

    private final studentService studentService;

    public StudentController(studentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<?> addStudentToClass(
        @PathVariable Long classId,
        @RequestBody AddStudentRequest request,
        @AuthenticationPrincipal Object user) { // TODO: Replace Object with your actual User principal type
        if (request.getUsername() == null || request.getUsername().isEmpty()) {
            return ResponseEntity.badRequest().body("Username cannot be empty");
            // soon must implement testing if student is registered in the database
        }

        try {
            // TODO: Update user.getUsername() to match your actual principal implementation
            StudentResponse response = studentService.addStudentToClass(classId, request.getUsername(), user.toString());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getClassRoster(
        @PathVariable Long classId,
        @AuthenticationPrincipal Object user) { // TODO: Replace Object with your actual User principal type
        try {
            // TODO: Update user.getUsername() to match your actual principal implementation
            Set<StudentResponse> roster = studentService.getClassRoster(classId, user.toString());
            return ResponseEntity.ok(roster);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}