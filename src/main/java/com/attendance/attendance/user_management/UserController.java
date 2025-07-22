package com.attendance.attendance.user_management;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.attendance.attendance.entities.User;
import com.attendance.attendance.repositories.UserRepository;
import com.attendance.attendance.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository repo;

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    // @GetMapping("/role/{role}")
    // public List<User> getUsersByRole(@PathVariable("role") User.Role role) {
    //     return userService.getUsersByRole(role);
    // }
    // @GetMapping("/{username}")
    // public User getUserByUsername(@PathVariable("username") String username) {
    //     return userService.getUserByUsername(username)
    //             .orElseThrow(() -> new RuntimeException("User not found"));
    // }
}
