package com.attendance.attendance.entities;

import java.time.LocalDateTime;

public class Attendance {

    private Long id;

    private Long sessionId;

    private Long studentId;

    private LocalDateTime checkInTime = LocalDateTime.now();

    private String ipAddress;

    // Getters & Setters
}
