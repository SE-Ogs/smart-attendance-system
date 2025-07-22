package com.attendance.attendance.session.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findByTeacherIdAndIsActiveTrue(Long teacherId);

    boolean existsByCodeandActiveTrue(String code);

    List<Session> findByClassId(Long classId);
}