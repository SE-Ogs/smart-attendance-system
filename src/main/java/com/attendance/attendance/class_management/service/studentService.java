package com.attendance.attendance.class_management.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.attendance.attendance.class_management.Response.StudentResponse;
import com.attendance.attendance.class_management.entity.ClassEntity;
import com.attendance.attendance.class_management.repository.ClassRepository;


@Service
public class StudentService {
    private final ClassRepository classRepository;

    public StudentService(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    public StudentResponse addStudentToClass(Long classId, String studentUsername, String teacherUsername) {
        ClassEntity classEntity = classRepository.findById(classId)
            .orElseThrow(() -> new RuntimeException("Class not found"));
        // TODO: Implement teacher and student logic
        StudentResponse response = new StudentResponse();
        response.setUsername(studentUsername);
        response.setFirstName("FirstName");
        response.setLastName("LastName");
        response.setEmail(studentUsername + "@example.com");
        return response;
    }

    public Set<StudentResponse> getClassRoster(Long classId, String requesterUsername) {
        ClassEntity classEntity = classRepository.findById(classId)
            .orElseThrow(() -> new RuntimeException("Class not found"));
        // TODO: Implement real roster logic
        return Set.of();
    }
}
