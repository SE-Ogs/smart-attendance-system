
package com.attendance.attendance.dashboard;

import com.attendance.attendance.session.Session;
import com.attendance.attendance.session.SessionService;
import com.attendance.attendance.classmanagement.ClassEntity;
import com.attendance.attendance.classmanagement.ClassService;
import com.attendance.attendance.ipAddress.AttendanceService;
import com.attendance.attendance.userManagement.User
package com.attendance.attendance.dashboard.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @GetMapping("/teacher")
    public Map<String, Object> teacherDashboard(HttpSession session) {
        Long teacherId = getCurrentUserId(session);

        if (teacherId == null) {
            return getMockDashboardWithError();
        }

        // Simulate successful dashboard response with mock data
        Map<String, Object> dashboard = new HashMap<>();
        dashboard.put("error", false);
        dashboard.put("classes", List.of(
                Map.of("id", 1, "name", "Math 101"),
                Map.of("id", 2, "name", "Science 102")
        ));
        dashboard.put("recentSessions", List.of(
                Map.of("id", 10, "code", "ABC123", "active", true),
                Map.of("id", 11, "code", "DEF456", "active", false)
        ));
        dashboard.put("totalStudents", 45);
        dashboard.put("attendanceSummary", Map.of(
                "totalSessions", 5,
                "totalAttendanceRecords", 180,
                "activeSessions", 2,
                "attendanceDetails", List.of(
                        Map.of("sessionId", 10, "attendanceCount", 25),
                        Map.of("sessionId", 11, "attendanceCount", 20)
                )
        ));

        return dashboard;
    }

    private Long getCurrentUserId(HttpSession session) {
        // Simulate a logged-in user ID; return null to simulate not logged in
        return 1001L;  // Change to null to simulate an error
    }

    private Map<String, Object> getMockDashboardWithError() {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", true);
        errorResponse.put("message", "User not logged in or invalid session");

        errorResponse.put("classes", Collections.emptyList());
        errorResponse.put("recentSessions", Collections.emptyList());
        errorResponse.put("totalStudents", 0);
        errorResponse.put("attendanceSummary", Map.of(
                "totalSessions", 0,
                "totalAttendanceRecords", 0,
                "activeSessions", 0,
                "attendanceDetails", Collections.emptyList()
        ));

        return errorResponse;
    }
}
