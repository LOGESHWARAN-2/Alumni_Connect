// 3. PostRepository.java
package com.example.AlumniConnect.repository;

import com.example.AlumniConnect.model.Post;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {
    List<Post> findAllByOrderByCreatedAtDesc();
}