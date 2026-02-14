// 1. UserRepository.java
package com.example.AlumniConnect.repository;

import com.example.AlumniConnect.model.User;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByCollegeEmail(String collegeEmail);
}