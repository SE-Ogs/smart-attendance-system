package com.attendance.attendance.session.Controller;

import com.attendance.attendance.session.model.Session;
import com.attendance.attendance.session.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/sessions")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    // Get active sessions for a teacher
    // @GetMapping("/active")
    // public ResponseEntity<List<Session>> getActiveSessions(@RequestParam Long teacherId) {
    //     List<Session> sessions = sessionService.getActiveSessions(teacherId);
    //     return ResponseEntity.ok(sessions);
    // }

    // End a session by ID
    // @PostMapping("/{id}/end")
    // public ResponseEntity<Void> endSession(@PathVariable Long id) {
    //     boolean ended = sessionService.endSession(id);
    //     if (ended) {
    //         return ResponseEntity.ok().build();
    //     } else {
    //         return ResponseEntity.notFound().build();
    //     }
    // }
}

