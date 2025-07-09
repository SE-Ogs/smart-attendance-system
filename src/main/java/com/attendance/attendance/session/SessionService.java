package com.attendance.attendance.session;
import java.security.SecureRandom;
import java.util.List;

import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;


@Service
public class SessionService {
    
    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private ClassService classService;

    private static final SecureRandom secureRandom = new SecureRandom();
    private final int codeLength = 6;

    public Session createSession(Long classId){
        // 1. Generate
        String code = generateUniqueCode("CSSE");

           // 2. Get current teacher
        Long teacherId = teacherContext.getCurrentTeacherId();

        // 3. Create session
        Session session = new Session(classId, code, teacherId);
        session.setActive(true); // mark as active

        // 4. Persist session first (so session ID exists)
        session = sessionRepository.save(session);

        // 5. Find all students in class
        List<User> students = classService.getStudentsInClass(classId);

        // 6. Send code to each student
        students.forEach(student -> 
            notificationService.sendCode(student.getId(), code, session.getId())
        );

        return session;
    }

    private String generateUniqueCode(String prefix){
        String code;
        do {
            code = generateCode(prefix, codeLength);
        } while (sessionRepository.existsByCodeandActiveTrue(code));
        return code;
    }

    private String generateCode (String prefix, int length) {
        int bound = (int) Math.pow(10, length);
        int number = secureRandom.nextInt(bound);
        String numpart = String.format("%0" + length + "d", number);
        return prefix + "-" + numpart;
    }

    

}