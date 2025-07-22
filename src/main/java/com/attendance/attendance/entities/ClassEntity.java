package com.attendance.attendance.entities;

import java.util.HashSet;
import java.util.Set;

public class ClassEntity {

    private Long id;

    private String className;

    private Long teacherId;

    private Set<User> students = new HashSet<>();
}

// Getters & Setters

