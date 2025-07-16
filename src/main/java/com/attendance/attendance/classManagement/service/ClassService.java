package com.attendance.attendance.classManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.attendance.attendance.classManagement.dto.ClassRequest;
import com.attendance.attendance.classManagement.entity.ClassEntity;
import com.attendance.attendance.classManagement.repository.ClassRepository;

public class ClassService {

    @Autowired
    private ClassRepository classRepository;
    public ClassEntity createClass(ClassRequest request, Long teacherId){
        ClassEntity classEntity = new ClassEntity(request.getClassName(), teacherId);
        return classRepository.save(classEntity);
    }

    public List<ClassEntity> getClassesbyTeacher(Long teacherId){
        return classRepository.findByTeacherId(teacherId);
    }
}
