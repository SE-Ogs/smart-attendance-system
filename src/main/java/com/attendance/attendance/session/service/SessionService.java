package com.attendance.attendance.session.service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attendance.attendance.session.model.Session;
import com.attendance.attendance.session.model.SessionRepository;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    // Get all active sessions for a teacher
    public List<Session> getActiveSessions(Long teacherId) {
        return sessionRepository.findByTeacherIdAndIsActiveTrue(teacherId);
    }

    private static final SecureRandom secureRandom = new SecureRandom();
    private final int codeLength = 6;

    public Session createSession(Long classId){
        // 1. Generate
        String code = generateUniqueCode("CSSE");

        // 3. Create session
        Session session = new Session();
        session.setIsActive(true);
        
        // 4. Persist session first (so session ID exists)
        session = sessionRepository.save(session);

        // 5. Find all students in class
        // List<User> students = classService.getStudentsInClass(classId);

        // 6. Send code to each student
        // students.forEach(student ->
        //     notificationService.sendCode(student.getId(), code, session.getId())
        // );

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

    // End a session
    public boolean endSession(Long sessionId) {
        Optional<Session> optionalSession = sessionRepository.findById(sessionId);
        if (optionalSession.isPresent()) {
            Session session = optionalSession.get();
            session.setIsActive(false);
            sessionRepository.save(session);
            return true;
        }
        return false;
    }
}