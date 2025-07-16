package com.attendance.attendance.dashboard;

import com.attendance.attendance.session.Session;
import com.attendance.attendance.session.SessionService;
import com.attendance.attendance.classmanagement.ClassEntity;
import com.attendance.attendance.classmanagement.ClassService;
import com.attendance.attendance.ipAddress.AttendanceService;
import com.attendance.attendance.userManagement.User;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private ClassService classService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private AttendanceService attendanceService;

    @GetMapping("/teacher")
    public Map<String, Object> teacherDashboard(HttpSession session) {
        Long teacherId = getCurrentUserId(session);
        if (teacherId == null) {
            throw new RuntimeException("User not logged in or invalid session");
        }

        List<ClassEntity> classes = classService.getClassesByTeacher(teacherId);
        List<Session> recentSessions = sessionService.getRecentSessions(teacherId);
        int totalStudents = classService.getTotalStudents(teacherId);

        Map<String, Object> attendanceSummary = attendanceService.getAttendanceSummaryForTeacher(teacherId);

        Map<String, Object> dashboard = new HashMap<>();
        dashboard.put("classes", classes);
        dashboard.put("recentSessions", recentSessions);
        dashboard.put("totalStudents", totalStudents);
        dashboard.put("attendanceSummary", attendanceSummary);

        return dashboard;
    }

    private Long getCurrentUserId(HttpSession session) {
        Object userObj = session.getAttribute("user");
        if (userObj instanceof User user) {
            return user.getId();
        }
        return null;
    }
}
