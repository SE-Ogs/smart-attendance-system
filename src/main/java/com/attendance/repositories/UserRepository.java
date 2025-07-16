package com.attendance.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.attendance.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    List<User> findByNameContaining(String keyword);

    long countByEmailEndingWith(String domain);

    void deleteByName(String name);

    Optional<User> findByUsername(String username);

    List<User> findByRole(User.Role role);
}
