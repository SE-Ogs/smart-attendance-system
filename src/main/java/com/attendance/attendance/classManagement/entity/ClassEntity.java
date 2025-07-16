package com.attendance.attendance.classManagement.entity;

public class ClassEntity {
    private String className;
    private Long teacherId;

    public ClassEntity(String className, Long teacherId) {
        this.className = className;
        this.teacherId = teacherId;
    }
}
