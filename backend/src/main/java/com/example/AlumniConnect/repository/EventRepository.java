// 4. EventRepository.java
package com.example.AlumniConnect.repository;

import com.example.AlumniConnect.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<Event, String> {
}