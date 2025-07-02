package com.attendance.attendance.ipAddress;

import com.attendance.attendance.ipAddress.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    int countBySessionId(Long sessionId);
}
