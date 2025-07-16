package com.attendance.attendance.ipaddress;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attendance.attendance.repository.AttendanceRepository;
import com.attendance.entities.Attendance;




@Service
public class StudentHistoryService{

    private AttendanceRepository attendanceRepository;

    private final Long hardCodedStudentId = 202301028L;
    private final Long hardCodedSessionId = 500201L;
    private final Long hardCodedClassId = 200300L;

    @Autowired
    public StudentHistoryService(AttendanceRepository attendanceRepository){
        this.attendanceRepository = attendanceRepository;
    }

    //Check class id using session id
    public Long getClassIdBySessionId(Long sessionId){
        if(sessionId.equals(hardCodedSessionId)){
            return hardCodedClassId;
        }

        return null;
    }

    //Check if student is enrolled in the class
    public boolean isStudentInClass(Long studentId, Long classId){
        return studentId.equals(hardCodedStudentId) && classId.equals(hardCodedClassId);
    }

    public List<Attendance> getAttendanceRecords(Long studentId, Long sessionId){
        return attendanceRepository.findByStudentIdAndSessionId(studentId, sessionId);
    }

    public List<String> attendanceHistory(Long studentId, Long sessionId, Long classId){
        if(!isStudentInClass(studentId, classId)){
            throw new RuntimeException("Student is not enrolled in this class");
        }

        List<Attendance> records = getAttendanceRecords(studentId, sessionId);

        return records.stream()
                .map(record -> "Checked in at: " + record.getCheckInTime() + ", IP: " + record.getIpAddress())
                .toList();
    }
 
    
}
