package com.attendance.attendance.session.model;

import java.time.Instant;

public class Notification {
    private Long sessionId;
    private String code;
    private Instant timestamp;

    public Notification() {
    }

    public Notification(Long sessionId, String code, Instant timestamp) {
        this.sessionId = sessionId;
        this.code = code;
        this.timestamp = timestamp;
    }

    // Getters and Setters
    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) { // Corrected: removed 'void' here
        this.code = code;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Notification{" +
               "sessionId=" + sessionId +
               ", code='" + code + '\'' +
               ", timestamp=" + timestamp +
               '}';
    }
}