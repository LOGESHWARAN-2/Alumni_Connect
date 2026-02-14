// 2. AlumniRepository.java
package com.example.AlumniConnect.repository;

import com.example.AlumniConnect.model.Alumni;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AlumniRepository extends MongoRepository<Alumni, String> {
    Optional<Alumni> findByCollegeEmail(String collegeEmail);
}