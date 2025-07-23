package com.attendance.attendance.ipaddress;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attendance.attendance.repository.AttendanceRepository;
import com.attendance.attendance.entities.Attendance;




@Service
public class StudentHistoryService{

    private AttendanceRepository attendanceRepository;

    private final String hardCodedStudentId = "202301028";
    private final String hardCodedSessionId = "500201";
    private final String hardCodedClassId = "200300";

    @Autowired
    public StudentHistoryService(AttendanceRepository attendanceRepository){
        this.attendanceRepository = attendanceRepository;
    }

    //Check class id using session id
    public String getClassIdBySessionId(String sessionId){
        if(sessionId.equals(hardCodedSessionId)){
            return hardCodedClassId;
        }

        return null;
    }

    //Check if student is enrolled in the class
    public boolean isStudentInClass(String studentId, String classId){
        return studentId.equals(hardCodedStudentId) && classId.equals(hardCodedClassId);
    }

    public List<Attendance> getAttendanceRecords(String studentId, String sessionId){
        return attendanceRepository.findByStudentIdAndSessionId(studentId, sessionId);
    }

    public List<String> attendanceHistory(String studentId, String sessionId, String classId){
        if(!isStudentInClass(studentId, classId)){
            throw new RuntimeException("Student is not enrolled in this class");
        }

        List<Attendance> records = getAttendanceRecords(studentId, sessionId);

        return records.stream()
                .map(record -> "Checked in at: " + record.getCheckInTime() + ", IP: " + record.getIpAddress())
                .toList();
    }
}
