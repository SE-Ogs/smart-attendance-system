package com.attendance.attendance.ipaddress;

import java.time.LocalDateTime;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//
@Service
public class AttendanceService {
    
    @Autowired
    private LocationService locationService;
    
    // Hardcoded active sessions (code -> session data)
    private final Map<String, Session> activeSessions = new HashMap<>();
    
    // Hardcoded attendance records (sessionId-studentId -> attendance)
    private final Map<String, Attendance> attendanceRecords = new HashMap<>();
    
    // Initialize with some sample data
    public AttendanceService() {
        // Add some sample active sessions
        activeSessions.put("ABC123", new Session(1L, "ABC123", true, LocalDateTime.now().plusHours(1)));
        activeSessions.put("DEF456", new Session(2L, "DEF456", true, LocalDateTime.now().plusHours(2)));
        activeSessions.put("GHI789", new Session(3L, "GHI789", false, LocalDateTime.now().minusHours(1)));
    }
    
    public boolean submitAttendance(String code, Long studentId, String ipAddress, Double latitude, Double longitude) {
        // 1. Find active session with code
        Session session = findActiveSession(code);
        if (session == null) {
            throw new RuntimeException("Invalid or expired code");
        }
        
        // 2. Check if already submitted
        String attendanceKey = session.getId() + "-" + studentId;
        if (attendanceRecords.containsKey(attendanceKey)) {
            throw new RuntimeException("Already submitted for this session");
        }
        
        // 3. Validate location (GPS coordinates and/or IP address)
        if (!locationService.isWithinCampusProximity(latitude, longitude, ipAddress)) {
            throw new RuntimeException("Location validation failed. You must be within campus proximity to submit attendance.");
        }
        
        // 4. Save attendance with location data
        Attendance attendance = new Attendance(
            session.getId(), 
            studentId, 
            ipAddress, 
            latitude, 
            longitude,
            LocalDateTime.now()
        );
        attendanceRecords.put(attendanceKey, attendance);
        
        return true;
    }
    
    private Session findActiveSession(String code) {
        Session session = activeSessions.get(code);
        if (session != null && session.isActive() && session.getExpiresAt().isAfter(LocalDateTime.now())) {
            return session;
        }
        return null;
    }
    
    public Map<String, Attendance> getAllAttendanceRecords() {
        return new HashMap<>(attendanceRecords);
    }
    
    public Map<String, Session> getAllActiveSessions() {
        return new HashMap<>(activeSessions);
    }
    
    public Map<String, Object> getLocationInfo() {
        return locationService.getLocationInfo();
    }
}

// Attendance model class
class Attendance {
    private Long sessionId;
    private Long studentId;
    private String ipAddress;
    private Double latitude;
    private Double longitude;
    private LocalDateTime submittedAt;
    
    public Attendance(Long sessionId, Long studentId, String ipAddress, 
                     Double latitude, Double longitude, LocalDateTime submittedAt) {
        this.sessionId = sessionId;
        this.studentId = studentId;
        this.ipAddress = ipAddress;
        this.latitude = latitude;
        this.longitude = longitude;
        this.submittedAt = submittedAt;
    }
    
    // Getters
    public Long getSessionId() { return sessionId; }
    public Long getStudentId() { return studentId; }
    public String getIpAddress() { return ipAddress; }
    public Double getLatitude() { return latitude; }
    public Double getLongitude() { return longitude; }
    public LocalDateTime getSubmittedAt() { return submittedAt; }
    
    // Setters
    public void setSessionId(Long sessionId) { this.sessionId = sessionId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }
    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }
    public void setSubmittedAt(LocalDateTime submittedAt) { this.submittedAt = submittedAt; }
}

// Session model class
class Session {
    private Long id;
    private String code;
    private boolean active;
    private LocalDateTime expiresAt;
    
    public Session(Long id, String code, boolean active, LocalDateTime expiresAt) {
        this.id = id;
        this.code = code;
        this.active = active;
        this.expiresAt = expiresAt;
    }
    
    // Getters
    public Long getId() { return id; }
    public String getCode() { return code; }
    public boolean isActive() { return active; }
    public LocalDateTime getExpiresAt() { return expiresAt; }
    
    // Setters
    public void setId(Long id) { this.id = id; }
    public void setCode(String code) { this.code = code; }
    public void setActive(boolean active) { this.active = active; }
    public void setExpiresAt(LocalDateTime expiresAt) { this.expiresAt = expiresAt; }
}