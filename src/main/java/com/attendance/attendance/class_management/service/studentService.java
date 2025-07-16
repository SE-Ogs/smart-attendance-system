package com.attendance.attendance.classManagement.Service;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.attendance.attendance.classManagement.repository.ClassRepository;

@Service
public class studentService {
    
    private final ClassRepository classRepository;
    private final UserRepository UserRepository;

       public StudentService(ClassRepository classRepository, UserRepository userRepository) {
        this.classRepository = classRepository;
        this.userRepository = userRepository;
    }

    public StudentResponse addStudentToClass(Long classId, String studentUsername, String teacherUsername) {
        Class classEntity = classRepository.findById(classId)
            .orElseThrow(() -> new ClassNotFoundException("Class not found"));

             if (!classEntity.getTeacher().getUsername().equals(teacherUsername)) {
            throw new UnauthorizedAccessException("You are not authorized to modify this class");
        }

        UserEntity student = userRepository.findByUsername(studentUsername)
                .orElseThrow(() -> new StudentNotFoundException("Student not found"));
    }

     // Check if student is already in the class
        if (classEntity.getStudents().contains(student)) {
            throw new StudentAlreadyInClassException("Student is already in this class");
        }

        // Add student to class
        classEntity.getStudents().add(student);
        classRepository.save(classEntity);

        return mapToStudentResponse(student);
    }

    public Set<StudentResponse> getClassRoster(Long classId, String requesterUsername) {
        Class classEntity = classRepository.findById(classId)
            .orElseThrow(() -> new ClassNotFoundException("Class not found"));

            boolean isTeacher = classEntity.getTeacher().getUsername().equals(requesterUsername);
            boolean isStudentInClass = classEntity.getStudents().stream()
                .anyMatch(student -> student.getUsername().equals(requesterUsername));

                if(!isTeacher && !isStudentInClass) {
                    throw new UnauthorizedAccessException("You are not authorized to view this class roster");
                }

                return classEntity.getStudents().stream()
                    .map(this::mapToStudentResponse)
                    .collect(Collectors.toSet());
    }
    
    private StudentResponse mapToStudentResponse(UserEntity student) {
        StudentResponse response = new StudentResponse();
        response.setUsername(student.getUsername());
        response.setFirstName(student.getFirstName());
        response.setLastName(student.getLastName());
        response.setEmail(student.getEmail());
        return response;
    }
}
