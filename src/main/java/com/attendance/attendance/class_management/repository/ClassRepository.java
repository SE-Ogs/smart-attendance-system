package com.attendance.attendance.class_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.attendance.attendance.class_management.entity.ClassEntity;

public interface ClassRepository extends JpaRepository<ClassEntity, Long> {
    List<ClassEntity> findByTeacherId(Long teacherId);
} 