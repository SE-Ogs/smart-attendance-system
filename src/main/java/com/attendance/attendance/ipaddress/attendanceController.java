package com.attendance.attendance.ipaddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/attendance")
public class attendanceController {
    
    @Autowired
    private attendanceService attendanceService;
    
    @PostMapping("/submit")
    public ResponseEntity<Map<String, Object>> submitAttendance(
            @RequestParam String code,
            @RequestParam Long studentId,
            @RequestParam String ipAddress,
            @RequestParam Double latitude,
            @RequestParam Double longitude) {
        
        try {
            boolean success = attendanceService.submitAttendance(code, studentId, ipAddress, latitude, longitude);
            
            if (success) {
                return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "Attendance submitted successfully"
                ));
            } else {
                return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", "Failed to submit attendance"
                ));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                "success", false,
                "message", e.getMessage()
            ));
        }
    }
    
    @GetMapping("/records")
    public ResponseEntity<Map<String, Attendance>> getAllAttendanceRecords() {
        return ResponseEntity.ok(attendanceService.getAllAttendanceRecords());
    }
    
    @GetMapping("/sessions")
    public ResponseEntity<Map<String, Session>> getAllActiveSessions() {
        return ResponseEntity.ok(attendanceService.getAllActiveSessions());
    }
    
    @GetMapping("/location-info")
    public ResponseEntity<Map<String, Object>> getLocationInfo() {
        return ResponseEntity.ok(attendanceService.getLocationInfo());
    }
}