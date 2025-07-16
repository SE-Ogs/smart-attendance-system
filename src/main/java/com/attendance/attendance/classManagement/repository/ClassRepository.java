package com.attendance.attendance.classManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.attendance.attendance.classManagement.entity.ClassEntity;

public interface ClassRepository extends JpaRepository<ClassEntity, Long> {
    List<ClassEntity> findByTeacherId(Long teacherId);
}