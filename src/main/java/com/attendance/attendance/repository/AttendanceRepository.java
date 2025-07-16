package com.attendance.attendance.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.attendance.entities.Attendance;


@Repository
public interface AttendanceRepository {
    List<Attendance> findByStudentIdAndSessionId(Long studentId, Long sessionId);
    
}