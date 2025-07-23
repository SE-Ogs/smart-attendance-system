package com.attendance.attendance.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.attendance.attendance.entities.ClassEntity;

public interface ClassRepository extends MongoRepository <ClassEntity, String> {
}
