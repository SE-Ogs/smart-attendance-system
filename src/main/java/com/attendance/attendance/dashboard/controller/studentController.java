package com.attendance.attendance.dashboard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

public class studentController {
@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    // Step 1: Inject required services (e.g., AttendanceService, NotificationService)
    // private final AttendanceService attendanceService;
    // private final NotificationService notificationService;

    // Step 2: Constructor injection for services
    // public DashboardController(AttendanceService attendanceService, NotificationService notificationService) {
    //     this.attendanceService = attendanceService;
    //     this.notificationService = notificationService;
    // }

    @GetMapping("/student")
    public Map<String, Object> studentDashboard(HttpSession session) {
        // Step 3: Retrieve the current student's ID from the session
        Long studentId = getCurrentUserId(session);

        // Step 4: Fetch the student's attendance history from the AttendanceService
        // List<Attendance> history = attendanceService.getStudentHistory(studentId);

        // Step 5: Fetch unread notification codes from the NotificationService
        // List<String> availableCodes = notificationService.getUnreadCodes(studentId);

        // Step 6: Calculate the attendance rate based on the attendance history
        // double attendanceRate = calculateAttendanceRate(history);

        // Step 7: Prepare the response map with attendance history, unread codes, and attendance rate
        Map<String, Object> dashboard = new HashMap<>();
        // dashboard.put("attendanceHistory", history);
        // dashboard.put("availableCodes", availableCodes);
        // dashboard.put("attendanceRate", attendanceRate);

        // Step 8: Return the dashboard map as the response
        return dashboard;
    }

    // Step 9: Implement helper methods like getCurrentUserId and calculateAttendanceRate
    // private Long getCurrentUserId(HttpSession session) {
    //     // Logic to retrieve the current user's ID from the session
    // }

    // private double calculateAttendanceRate(List<Attendance> history) {
    //     // Logic to calculate attendance rate
    // }
}