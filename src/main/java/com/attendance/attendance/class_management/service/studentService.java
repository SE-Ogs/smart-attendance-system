package com.attendance.attendance.class_management.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.attendance.attendance.class_management.Response.StudentResponse;
import com.attendance.attendance.entities.ClassEntity;
import com.attendance.attendance.repository.ClassRepository;


@Service
public class StudentService {
    private final ClassRepository classRepository;

    public StudentService(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    public StudentResponse addStudentToClass(Long classId, String studentUsername, String teacherUsername) {
        ClassEntity classEntity = classRepository.findById(classId)
            .orElseThrow(() -> new RuntimeException("Class not found"));
        
        StudentResponse response = new StudentResponse();
        response.setUsername(studentUsername);
        response.setFirstName("FirstName"); // TODO: Fetch real data
        response.setLastName("LastName");  // TODO: Fetch real data
        response.setEmail(studentUsername + "@example.com");
        return response;
    }

    public Set<StudentResponse> getClassRoster(Long classId, String requesterUsername) {
        ClassEntity classEntity = classRepository.findById(classId)
            .orElseThrow(() -> new RuntimeException("Class not found"));
        return Set.of(); // TODO: Return real roster
    }
}