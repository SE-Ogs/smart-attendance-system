package com.attendance.attendance.entities;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Sessions")
public class Session {

    @Id
    private String id;
    private String classId;
    private String sessionCode;
    private LocalDateTime startTime = LocalDateTime.now();
    private String teacherId;
    private boolean active = true;

    // Getters
    public String getId() {
        return id;
    }

    public String getClassId() {
        return classId;
    }

    public String getSessionCode() {
        return sessionCode;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public boolean isActive() {
        return active;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public void setSessionCode(String sessionCode) {
        this.sessionCode = sessionCode;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    // toString
    @Override
    public String toString() {
        return "Session{"
                + "id='" + id + '\''
                + ", classId='" + classId + '\''
                + ", sessionCode='" + sessionCode + '\''
                + ", startTime=" + startTime
                + ", teacherId='" + teacherId + '\''
                + ", active=" + active
                + '}';
    }
}
