package com.attendance.attendance.repository;

import com.attendance.attendance.session.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface NotificationRepository extends MongoRepository<Notification, String> {
    List<Notification> findByStudentId(Long studentId);
}