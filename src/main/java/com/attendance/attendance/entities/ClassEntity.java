package com.attendance.attendance.entities;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Classes")
public class ClassEntity {

    @Id
    private String id;
    private String className;
    private String teacherId;

    @DBRef
    private Set<User> students = new HashSet<>();

    // Getters
    public String getId() {
        return id;
    }

    public String getClassName() {
        return className;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public Set<User> getStudents() {
        return students;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public void setStudents(Set<User> students) {
        this.students = students;
    }

    // toString
    @Override
    public String toString() {
        return "ClassEntity{"
                + "id='" + id + '\''
                + ", className='" + className + '\''
                + ", teacherId='" + teacherId + '\''
                + ", students=" + students
                + '}';
    }
}
