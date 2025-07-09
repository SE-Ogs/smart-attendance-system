package com.attendance.attendance.session.service; // Assuming this package

import com.attendance.attendance.session.model.Notification;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class NotificationService {
    // studentId -> list of pending notifications
    private final ConcurrentMap<Long, List<Notification>> notifications = new ConcurrentHashMap<>();

    public void sendCode(Long studentId, String code, Long sessionId) {
        notifications
            .computeIfAbsent(studentId, id -> new CopyOnWriteArrayList<>())
            .add(new Notification(sessionId, code, Instant.now())); // Added Instant.now()
    }

    public List<Notification> fetchAndClear(Long studentId) {
        List<Notification> list = notifications.remove(studentId);
        return list != null ? list : Collections.emptyList();
    }
}