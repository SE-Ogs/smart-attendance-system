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
import com.attendance.entities.Attendance;
import com.attendance.attendance.repository.AttendanceRepository;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/reports/class/{id}")
@CrossOrigin(origins = "*")
public class AttendanceController {
    @Autowired
    private AttendanceRepository attendanceRepository;

    @GetMapping()
    public List<Attendance> getAttendanceReports(@PathVariable("id") Long sessionId) {
        return attendanceRepository.findByStudentIdAndSessionId(null, sessionId); // Returns all attendance for the session
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Attendance addAttendance(@RequestBody Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    @PutMapping("/{attendanceId}")
    public Attendance updateAttendance(@PathVariable("attendanceId") Long attendanceId, @RequestBody Attendance updatedAttendance) {
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
    public void deleteAttendance(@PathVariable("attendanceId") Long attendanceId) {
        if (!attendanceRepository.existsById(attendanceId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Attendance report not found");
        }
        attendanceRepository.deleteById(attendanceId);
    }
}

