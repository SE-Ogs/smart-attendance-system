package com.attendance.attendance.class_management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attendance.attendance.class_management.Requests.ClassRequest;
import com.attendance.attendance.entities.ClassEntity;
import com.attendance.attendance.entities.User;

@Service
public class ClassService {

   // @Autowired
   // private ClassRepository classRepository;

    // Create a class
    // public ClassEntity createClass(ClassRequest request, User teacher) {
    //     if (teacher == null || teacher.getRole() != User.Role.TEACHER) {
    //         throw new IllegalArgumentException("User is not a teacher");
    //     }

    //     // Check for duplicate classes
    //     String className = request.getClassName();
    //     if (classRepository.existsByClassNameAndTeacherId(className, teacher.getId())) {
    //         throw new IllegalArgumentException("Class already exists for this teacher");
    //     }

    //     // Create and save the class
    //     ClassEntity classEntity = new ClassEntity();
    //     classEntity.setClassName(className);
    //     classEntity.setTeacherId(teacher.getId());
    //     return classRepository.save(classEntity);
    // }

    // Get classes by teacher
    // public List<ClassEntity> getClassesByTeacher(User teacher) {
    //     if (teacher == null || teacher.getRole() != User.Role.TEACHER) {
    //         throw new IllegalArgumentException("User is not a teacher");
    //     }
    //     return classRepository.findByTeacherId(teacher.getId());
    // }
}