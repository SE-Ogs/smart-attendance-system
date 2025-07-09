package com.attendance.attendance.ipAddress;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

@RestController
@RequestMapping("/api/attendance/session/{id}")
@CrossOrigin(origins = "*")
public class SessionController {
    private List<Session> sessions = new ArrayList<>();

    @GetMapping()
    @ResponseBody
    public Attendance getAttendance(@PathVariable int id) {
        sessions.add(new Session(1, "John Doe", "2023-05-15", "10:00 AM", true));
        sessions.add(new Session(2, "Jane Smith", "2023-05-15", "10:05 AM", false));
        sessions.add(new Session(3, "Bob Johnson", "2023-05-16", "09:30 AM", true));

        Session session = sessions.stream()
            .filter(s -> s.getId() == id)
            .findFirst()
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Session not found with id: " + id));
        
        return ResponseEntity.ok(session);    
    }

    private static class Session {
        private int id;
        private String name;
        private String date;
        private String time;
        private boolean present;

        public Session(int id, String name, String date, String time, boolean present) {
            this.id = id;
            this.name = name;
            this.date = date;
            this.time = time;
            this.present = present;
        }

        // Getters
        public int getId() { return id; }
        public String getName() { return name; }
        public String getDate() { return date; }
        public String getTime() { return time; }
        public boolean isPresent() { return present; }
    }
}


