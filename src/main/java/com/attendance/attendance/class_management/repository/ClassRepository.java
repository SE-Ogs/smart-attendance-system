package com.attendance.attendance.class_management.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.attendance.attendance.entities.ClassEntity;

@Repository
public interface ClassRepository extends MongoRepository<ClassEntity, String> {

    // Find all classes by teacher ID
    List<ClassEntity> findByTeacherId(String teacherId);

    // Check if a class with the same name exists for a specific teacher
    boolean existsByClassNameAndTeacherId(String className, String teacherId);
}