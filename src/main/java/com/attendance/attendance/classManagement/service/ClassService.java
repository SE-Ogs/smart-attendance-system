package com.attendance.attendance.classManagement.service;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.attendance.attendance.classManagement.dto.ClassRequest;
import com.attendance.entities.ClassEntity;
import com.attendance.entities.User;

public class ClassService {

    //use a HashMap to prevent duplicate classes per teacher
    private Map<String, ClassEntity> classMap = new HashMap<>();

    public ClassEntity createClass(ClassRequest request, User teacher){
        if (teacher == null || teacher.getRole() != User.Role.TEACHER) {
            throw new IllegalArgumentException("User is not a teacher");
        }
        String key = request.getClassName() + "_" + teacher.getId();
        if (classMap.containsKey(key)) {
            //return the existing class if duplicate
            return classMap.get(key);
        }
        ClassEntity classEntity = new ClassEntity();
        classEntity.setClassName(request.getClassName());
        classEntity.setTeacherId(teacher.getId());
        classMap.put(key, classEntity);
        return classEntity;
    }

    public List<ClassEntity> getClassesbyTeacher(User teacher){
        List<ClassEntity> result = new ArrayList<>();
        if (teacher == null || teacher.getRole() != User.Role.TEACHER) {
            return result;
        }
        for (ClassEntity c : classMap.values()) {
            if (c.getTeacherId() != null && c.getTeacherId().equals(teacher.getId())) {
                result.add(c);
            }
        }
        return result;
    }
}
