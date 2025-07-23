package com.attendance.attendance.ipaddress;

import java.time.LocalDateTime;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.attendance.attendance.entities.Attendance;
import com.attendance.attendance.entities.Session;
import com.attendance.attendance.repository.AttendanceRepository;
import org.springframework.transaction.annotation.Transactional;


//
@Service
public class AttendanceService {
    
    @Autowired
   private LocationService locationService;

    @Autowired
    private AttendanceRepository attendanceRepository;
    
    // Initialize with some sample data
    public AttendanceService() {
        // Add some sample active sessions
        // activeSessions.put("ABC123", new Session(1L, "ABC123", true, LocalDateTime.now().plusHours(1)));
        // activeSessions.put("DEF456", new Session(2L, "DEF456", true, LocalDateTime.now().plusHours(2)));
        // activeSessions.put("GHI789", new Session(3L, "GHI789", false, LocalDateTime.now().minusHours(1)));
    }
    
    @Transactional
    public Attendance submitAttendance(Session session, String studentId, String ipAddress, Double latitude, Double longitude) {
        // 1. Check if already submitted
        if (!attendanceRepository.findByStudentIdAndSessionId(studentId, session.getId()).isEmpty()) {
            throw new RuntimeException("Already submitted for this session");
        }
        // 2. Validate location (GPS coordinates and/or IP address)
        if (!locationService.isWithinCampusProximity(latitude, longitude, ipAddress)) {
            throw new RuntimeException("Location validation failed. You must be within campus proximity to submit attendance.");
        }
        // 3. Save attendance with location data
        Attendance attendance = new Attendance();
        attendance.setSessionId(session.getId());
        attendance.setStudentId(studentId);
        attendance.setIpAddress(ipAddress);
        attendance.setCheckInTime(java.time.LocalDateTime.now());
        attendance.setLatitude(latitude);
        attendance.setLongitude(longitude);
        return attendanceRepository.save(attendance);
    }
    
    public Map<String, Object> getLocationInfo() {
        return locationService.getLocationInfo();
    }
}