package com.attendance.attendance.repository;

import com.attendance.entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    Session findBySessionCodeAndActiveTrue(String sessionCode);
} 