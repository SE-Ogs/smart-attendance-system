package com.attendance.attendance.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.attendance.attendance.entities.User;

public interface UserRepository extends MongoRepository<User, String> {

}
