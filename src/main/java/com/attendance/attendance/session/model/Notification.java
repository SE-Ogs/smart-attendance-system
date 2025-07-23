package com.attendance.attendance.session.model;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "notifications")
public class Notification {
    @Id
    private String id; 
    private Long sessionId;
    private String code;
    private Instant timestamp;
    private Long studentId; 

    public Notification() {
    }

    public Notification(Long sessionId, String code, Instant timestamp, Long studentId) {
        this.sessionId = sessionId;
        this.code = code;
        this.timestamp = timestamp;
        this.studentId = studentId;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) { 
        this.code = code;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "Notification{" +
               "id='" + id + '\'' +
               ", sessionId=" + sessionId +
               ", code='" + code + '\'' +
               ", timestamp=" + timestamp +
               ", studentId=" + studentId +
               '}';
    }
}