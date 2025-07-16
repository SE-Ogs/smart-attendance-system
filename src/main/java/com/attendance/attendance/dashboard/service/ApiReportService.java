package com.attendance.attendance.dashboard.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attendance.attendance.repository.AttendanceRepository;
import com.attendance.attendance.repository.ClassRepository;
import com.attendance.attendance.session.model.Session;
import com.attendance.attendance.session.model.SessionRepository;
import com.attendance.entities.Attendance;

@Service
public class ApiReportService
{
    @Autowired private AttendanceRepository attendanceRepository;
    @Autowired private SessionRepository sessionRepository;
    @Autowired private ClassRepository classRepository;

    // mapping for sessions, attendance, labot napud ang overall total
    public Map<String, Object> getClassAttendanceReport (Long classId)
    {
        List<Session> sessions = sessionRepository.findByClassId(classId);
        List<Attendance> allAttendace = attendanceRepository.findBySessionClassId(classId);

        Map<String, Object> report = new HashMap<>();
        report.put("totalSessions", sessions.size());
        report.put("totalAttendance",allAttendace.size());
        report.put("sessionDetails", sessions.stream().map(session -> {
            long count = allAttendace.stream().filter(a -> a.getSessionId().equals(session.getId())).count();
            return Map.of("sessionId", session.getId(), "code", session.getSessionCode(), "attendees", count);
        }).collect(Collectors.toList()));

        return report;
    }
}