package com.attendance.attendance.classManagement.controller;

import java.util.List;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.attendance.attendance.classManagement.dto.ClassRequest;
import com.attendance.attendance.classManagement.entity.ClassEntity;
import com.attendance.attendance.classManagement.service.ClassService;

@RestController
@RequestMapping("/api/classes")
public class ClassController {

    @Autowired
    private ClassService classService;

    //teacher creating a class
    @PostMapping
    public ResponseEntity<?> createClass(@RequestBody ClassRequest request, Authentication auth) {
        try {
            //check if user is teacher
            if(!isTeacher(auth)){
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Only teachers can create classes.");
            }

            //get teacher id
            Long teacherId = getTeacherId(auth);

            //create and save class
            ClassEntity savedClass = classService.createClass(request, teacherId);
            return ResponseEntity.ok(savedClass);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Error creating class: " + e.getMessage());
        }
    }

    //teacher viewing the class
    @GetMapping
    public ResponseEntity<?> getClasses(Authentication auth){
        try{
            Long userId = getUserId(auth);
            List<ClassEntity> classes;

            if(isTeacher(auth)){
                classes = classService.getClassesbyTeacher(userId);
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Only teachers can view classes.");
            }
            return ResponseEntity.ok(classes);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Error fetching classes: " + e.getMessage());
        }
    }

    private boolean isTeacher(Authentication auth){
        return true;
    }

    private Long getUserId(Authentication auth){
        return null;
    }

    private Long getTeacherId(Authentication auth){
        return null;
    }
}

