package com.attendance.attendance.session.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long classId;
    private Long teacherId;
    private String code;
    private boolean isActive = true;

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        this.isActive = active;
    }

    public Object getId() {
        // TODO : from api report service
        throw new UnsupportedOperationException("Unimplemented method 'getId'");
    }

    public Object getSessionCode() {
        // TODO : from api report service
        throw new UnsupportedOperationException("Unimplemented method 'getSessionCode'");
    }

}
