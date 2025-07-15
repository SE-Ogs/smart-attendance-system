package com.attendance.attendance.ipAddress;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import com.damayo.damayo.Models.TodoModel;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

@Controller
@RequestMapping("/api/reports/class/{id}")
@CrossOrigin(origins = "*")
public class AttendanceController {
    List<Attendance> AttendanceReports = new ArrayList<>();

    @GetMapping()
    @ResponseBody
    public Attendance getAttendance(@PathVariable int id) {
        return AttendanceReports.stream()
            .filter(attendance -> attendance.getId() == id)
            .findFirst()
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Attendance report not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Attendance addAttendance(@RequestParam String attendance) {
        System.out.println("Received attendance report: " + attendance); // Debug log
        Attendance attendance = new Attendance("Test", "Test", "Test");
        attendance.setId(idCounter++);
        AttendanceReports.add(attendance);
        System.out.println("Added todo with id: " + attendance.getId()); // Debug log
        return attendance;
    }

    @PutMapping
    @ResponseBody
    public Attendance updateAttendance(@RequestParam int id, @RequestParam String attendance) {
        Attendance attendance = getAttendance(id);
        attendance.setPresent(true);
        attendance.setDate("Date");
        return attendance;
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAttendance(@RequestParam int id) {
        Attendance attendance = getAttendance(id);
        AttendanceReports.remove(attendance);
    }
}
