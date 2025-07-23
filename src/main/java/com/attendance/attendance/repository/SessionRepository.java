package com.attendance.attendance.repository;

import com.attendance.attendance.entities.Session;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends MongoRepository<Session, String> {
    Session findBySessionCodeAndActiveTrue(String sessionCode);
} 