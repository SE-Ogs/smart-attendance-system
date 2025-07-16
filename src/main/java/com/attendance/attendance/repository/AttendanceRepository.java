package com.attendance.attendance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.attendance.entities.Attendance;


@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long>{
    List<Attendance> findByStudentIdAndSessionId(Long studentId, Long sessionId);

    List<Attendance> findBySessionClassId(Long classId);

}