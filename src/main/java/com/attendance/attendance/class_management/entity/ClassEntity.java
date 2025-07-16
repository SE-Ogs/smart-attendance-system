package com.attendance.attendance.class_management.entity;

public class ClassEntity {
    private Long id;
    private String className;
    private Long teacherId;

    public ClassEntity() {}

    public ClassEntity(String className, Long teacherId) {
        this.className = className;
        this.teacherId = teacherId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }
} 