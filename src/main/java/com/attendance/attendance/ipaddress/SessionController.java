package com.attendance.attendance.ipaddress;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.attendance.entities.Session;
import com.attendance.attendance.repository.SessionRepository;

@RestController
@RequestMapping("/api/attendance/session")
@CrossOrigin(origins = "*")
public class SessionController {
    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping("/{id}")
    public Session getSession(@PathVariable Long id) {
        return sessionRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Session not found with id: " + id));
    }

    @GetMapping()
    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }
}