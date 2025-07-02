package com.attendance.attendance.ipaddress;

import com.attendance.attendance.ipaddress.repository.SessionRepository;
import com.attendance.attendance.ipaddress.repository.AttendanceRepository;

import java.time.LocalDateTime;
import java.util.Set;

import javax.management.RuntimeErrorException;

import org.springframework.boot.web.server.autoconfigure.ServerProperties.Reactive.Session;
import org.springframework.stereotype.Service;

@Service
public class attendanceService {

    //ip sa school
    private final Set<String> CAMPUS_IPS = Set.of(
        "127.0.0.1"
    );

    //assuming naaay mga ingani nga tables sa data layer
    // private final SessionRepository sessionRepository;
    // private final AttendanceRepository attendanceRepository;

    // public AttendanceService(SessionRepository sessionRepository, AttendanceRepository attendanceRepository){
    //     this.sessionRepository = sessionRepository;
    //     this.attendanceRepository = attendanceRepository;
    // }

    public boolean submitAttendance(String code, Long studentId, String ipAddress) {
        // 1. Check if IP is from campus
        if (!isValidCampusIP(ipAddress)) {
            throw new RuntimeException("Must be on campus to submit attendance");
        }
        
        // 2. Find active session with code
        Session session = sessionRepository.findByCodeAndActive(code, true);
        if (session == null) {
            throw new RuntimeException("Invalid or expired code");
        }
        
        // 3. Check if already submitted
        if (attendanceRepository.existsBySessionIdAndStudentId(session.getId(), studentId)) {
            throw new RuntimeException("Already submitted for this session");
        }
        
        // 4. Save attendance
        Attendance attendance = new Attendance(session.getId(), studentId, ipAddress);
        attendanceRepository.save(attendance);
        return true;
    }
    
    private boolean isValidCampusIP(String ip) {
        return CAMPUS_IPS.stream().anyMatch(ip::startsWith);
    }

}
