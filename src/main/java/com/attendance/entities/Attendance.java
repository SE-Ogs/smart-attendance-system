package com.attendance.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "session_id")
    private Long sessionId;

    @Column(name = "student_id")
    private Long studentId;

    private LocalDateTime checkInTime = LocalDateTime.now();

    private String ipAddress;

    // Getters & Setters
    public Long getId(){
        return id;
    }

    public Long getSessionId(){
        return sessionId;
    }

    public Long getStudentId(){
        return studentId;
    }

    public String getIpAddress(){
        return ipAddress;
    }

    public LocalDateTime getCheckInTime(){
        return checkInTime;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public void setCheckInTime(LocalDateTime checkInTime) {
        this.checkInTime = checkInTime;
    }

    public void setIpAddress(String ipAddress){
        this.ipAddress = ipAddress;
    }
}
