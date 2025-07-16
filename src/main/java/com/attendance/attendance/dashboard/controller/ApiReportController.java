package com.attendance.attendance.dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.attendance.attendance.dashboard.service.ApiReportService;

@RestController
@RequestMapping("/api/reports")
public class ApiReportController {

    @Autowired private ApiReportService reportService;

    @GetMapping("/class/{classId}")
    public ResponseEntity<?> getClassReport(@PathVariable Long classId) {
        return ResponseEntity.ok(reportService.getClassAttendanceReport(classId));
    }
}
