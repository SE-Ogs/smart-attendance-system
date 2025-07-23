package com.attendance.attendance.entities;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Attendance")
public class Attendance {

    @Id
    private String id;
    private String sessionId;
    private String studentId;
    private LocalDateTime checkInTime = LocalDateTime.now();
    private String ipAddress;
    private double latitude;
    private double longitude;

    // Getters
    public String getId() {
        return id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getStudentId() {
        return studentId;
    }

    public LocalDateTime getCheckInTime() {
        return checkInTime;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setCheckInTime(LocalDateTime checkInTime) {
        this.checkInTime = checkInTime;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void setLatitude(double latitude){
        this.latitude = latitude;
    }

    public void setLongitude(double longitude){
        this.longitude = longitude;
    }

    // toString
    @Override
    public String toString() {
        return "Attendance{"
                + "id='" + id + '\''
                + ", sessionId='" + sessionId + '\''
                + ", studentId='" + studentId + '\''
                + ", checkInTime=" + checkInTime
                + ", ipAddress='" + ipAddress + '\''
                + ", latitude='" + latitude + '\''
                + ", longitude='" + longitude + '\''
                + '}';
    }
}
