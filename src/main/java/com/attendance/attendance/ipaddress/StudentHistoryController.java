package com.attendance.attendance.ipaddress;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/attendance/my-history")
public class StudentHistoryController {

    private final StudentHistoryService studentHistoryService;

    @Autowired
    public StudentHistoryController(StudentHistoryService studentHistoryService){
        this.studentHistoryService = studentHistoryService;
    }

    @GetMapping
    public List<String> getHistory(
        @RequestParam String studentId, @RequestParam String sessionId
    ){
        String classId = studentHistoryService.getClassIdBySessionId(sessionId);
        if(classId == null){
            throw new RuntimeException("Invalid session Id");
        }

        return studentHistoryService.attendanceHistory(studentId, sessionId, classId);

    }
}
