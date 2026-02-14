// 5. FollowerRepository.java
package com.example.AlumniConnect.repository;

import com.example.AlumniConnect.model.Follower;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FollowerRepository extends MongoRepository<Follower, String> {
}