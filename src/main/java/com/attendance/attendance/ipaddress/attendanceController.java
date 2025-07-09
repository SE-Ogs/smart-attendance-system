package com.attendance.attendance.ipaddress;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("api/attendance")
public class attendanceController {
    
    @PostMapping("/submit")
    public ResponseEntity<?> submitAttendance(@RequestParam String code, HttpServletRequest request){
        try {
            //get ip address from the request
            // String ipAddresss = request.getRemoteAddr();
            // //get student id
            // Long studentId = getAuthenticatedStudentId();
            // //use attendance service to submit attendance
            // boolean result = attendanceService.submitAttendance(code,studentId, ipAddresss);
            return ResponseEntity.ok("success.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
}
