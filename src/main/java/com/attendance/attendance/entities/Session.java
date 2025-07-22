package com.attendance.attendance.entities;

import java.time.LocalDateTime;

public class Session {

    private Long id;

    private Long classId;

    private String sessionCode;

    private LocalDateTime startTime = LocalDateTime.now();

    private Long teacherId;

    private boolean active = true;

    // Getters & Setters
}
