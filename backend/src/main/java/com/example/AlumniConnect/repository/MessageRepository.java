// 7. MessageRepository.java
package com.example.AlumniConnect.repository;

import com.example.AlumniConnect.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<Message, String> {
}