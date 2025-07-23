package com.attendance.attendance.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.attendance.attendance.entities.Attendance;


@Repository
public interface AttendanceRepository extends MongoRepository<Attendance, String>{
    List<Attendance> findByStudentIdAndSessionId(String studentId, String sessionId);

    List<Attendance> findBySessionClassId(String classId);

}