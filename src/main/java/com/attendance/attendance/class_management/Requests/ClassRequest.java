package com.attendance.attendance.class_management.Requests;

public class ClassRequest {

    private String className;

    // Default constructor
    public ClassRequest() {
    }

    // Parameterized constructor
    public ClassRequest(String className) {
        this.className = className;
    }

    // Getter for className
    public String getClassName() {
        return className;
    }

    // Setter for className
    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "ClassRequest{" +
                "className='" + className + '\'' +
                '}';
    }
}