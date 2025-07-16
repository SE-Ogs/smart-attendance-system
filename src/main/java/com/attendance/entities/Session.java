package com.attendance.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sessions")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "class_id")
    private Long classId;

    private String sessionCode;

    private LocalDateTime startTime = LocalDateTime.now();

    @Column(name = "teacher_id")
    private Long teacherId;

    private boolean active = true;

    // Getters & Setters
}
