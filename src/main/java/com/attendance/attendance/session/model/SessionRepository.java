package com.attendance.attendance.session.model;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findByTeacherIdAndIsActiveTrue(Long teacherId);
}