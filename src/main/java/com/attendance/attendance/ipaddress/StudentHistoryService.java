package com.attendance.attendance.ipaddress;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;



@Service
public class StudentHistoryService{

    private final Long hardCodedStudentId = 202301028L;
    private final Long hardCodedSessionId = 500201L;
    private final Long hardCodedClassId = 200300L;

    //Check class id using session id
    public Long getClassIdBySessionId(Long sessionId){
        if(sessionId.equals(hardCodedSessionId)){
            return hardCodedClassId;
        }

        return null;
    }

    //Check if student is enrolled in the class
    public boolean isStudetnInClass(Long studentId, Long classId){
        return studentId.equals(hardCodedStudentId) && classId.equals(hardCodedClassId);
    }

    //Return student history
    public List<String> attendanceHistory(Long studentId, Long classId){
        if(!isStudetnInClass(studentId, classId)){
            throw new RuntimeException("Student is not enrolled in class");
        }


        List<String> history = new ArrayList();
        history.add("Late");
        history.add("Present");
        history.add("Absent");

        return history;
    }
}
