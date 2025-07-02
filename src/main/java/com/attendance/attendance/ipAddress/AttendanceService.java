package com.attendance.attendance.ipAddress;

import com.attendance.attendance.session.Session;
import com.attendance.attendance.session.SessionRepository;
import com.attendance.attendance.attendance.AttendanceRepository;
import com.attendance.attendance.classManagement.ClassService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AttendanceService {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private ClassService classService;

    public Map<String, Object> getAttendanceSummaryForTeacher(Long teacherId) {
        List<Session> sessions = sessionRepository.findByTeacherId(teacherId);

        int totalSessions = sessions.size();
        int totalCheckIns = 0;
        int totalPossible = 0;

        for (Session session : sessions) {
            int expected = classService.getStudentsInClass(session.getClassId()).size();
            int actual = attendanceRepository.countBySessionId(session.getId());

            totalCheckIns += actual;
            totalPossible += expected;
        }

        double rate = (totalPossible == 0) ? 0.0 : (double) totalCheckIns / totalPossible * 100;

        Map<String, Object> summary = new HashMap<>();
        summary.put("totalSessions", totalSessions);
        summary.put("totalCheckIns", totalCheckIns);
        summary.put("averageAttendanceRate", rate);

        return summary;
    }
}
