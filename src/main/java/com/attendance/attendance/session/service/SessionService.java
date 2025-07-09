package com.attendance.attendance.session.service;

import com.attendance.attendance.session.model.Session;
import com.attendance.attendance.session.model.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    // Get all active sessions for a teacher
    public List<Session> getActiveSessions(Long teacherId) {
        return sessionRepository.findByTeacherIdAndIsActiveTrue(teacherId);
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