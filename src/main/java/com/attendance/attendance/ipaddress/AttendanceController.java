package com.attendance.attendance.ipaddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import com.attendance.attendance.entities.Attendance;
import com.attendance.attendance.repository.AttendanceRepository;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import org.springframework.web.bind.annotation.RequestBody;
import com.attendance.attendance.entities.Session;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import com.attendance.attendance.repository.SessionRepository;

@RestController
@RequestMapping("/api/reports/class/{id}")
@CrossOrigin(origins = "*")
public class AttendanceController {
    @Autowired
    private AttendanceRepository attendanceRepository;
    @Autowired
    private AttendanceService attendanceService;
    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping()
    public List<Attendance> getAttendanceReports(@PathVariable("id") String sessionId) {
        return attendanceRepository.findByStudentIdAndSessionId(null, sessionId); // Returns all attendance for the session
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Attendance addAttendance(@RequestBody Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    @PostMapping("/api/attendance/submit")
    public ResponseEntity<?> submitAttendance(@RequestBody AttendanceSubmitRequest request) {
        // 1. Find session by code
        Session session = sessionRepository.findBySessionCodeAndActiveTrue(request.getCode());
        if (session == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or expired session code");
        }
        // 2. Check if already submitted
        List<Attendance> existing = attendanceRepository.findByStudentIdAndSessionId(request.getStudentId(), session.getId());
        if (!existing.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Attendance already submitted for this session");
        }
        // 3. Validate and submit attendance
        try {
            Attendance attendance = attendanceService.submitAttendance(session, request.getStudentId(), request.getIpAddress(), request.getLatitude(), request.getLongitude());
            return ResponseEntity.status(HttpStatus.CREATED).body(attendance);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{attendanceId}")
    public Attendance updateAttendance(@PathVariable("attendanceId") String attendanceId, @RequestBody Attendance updatedAttendance) {
        Attendance attendance = attendanceRepository.findById(attendanceId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Attendance report not found"));
        // Update fields as needed
        attendance.setSessionId(updatedAttendance.getSessionId());
        attendance.setStudentId(updatedAttendance.getStudentId());
        attendance.setCheckInTime(updatedAttendance.getCheckInTime());
        attendance.setIpAddress(updatedAttendance.getIpAddress());
        return attendanceRepository.save(attendance);
    }

    @DeleteMapping("/{attendanceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAttendance(@PathVariable("attendanceId") String attendanceId) {
        if (!attendanceRepository.existsById(attendanceId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Attendance report not found");
        }
        attendanceRepository.deleteById(attendanceId);
    }

    // DTO for attendance submission
    public static class AttendanceSubmitRequest {
        private String code;
        private String studentId;
        private String ipAddress;
        private Double latitude;
        private Double longitude;
        // Getters and setters
        public String getCode() { return code; }
        public void setCode(String code) { this.code = code; }
        public String getStudentId() { return studentId; }
        public void setStudentId(String studentId) { this.studentId = studentId; }
        public String getIpAddress() { return ipAddress; }
        public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }
        public Double getLatitude() { return latitude; }
        public void setLatitude(Double latitude) { this.latitude = latitude; }
        public Double getLongitude() { return longitude; }
        public void setLongitude(Double longitude) { this.longitude = longitude; }
    }
}

