// 6. ConversationRepository.java
package com.example.AlumniConnect.repository;

import com.example.AlumniConnect.model.Conversation;
import java.util.Optional;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ConversationRepository extends MongoRepository<Conversation, String> {
    // participants: { $all: [senderId, receiverId] } — same logic as Node
    @Query("{ 'participants': { $all: [?0, ?1] } }")
    Optional<Conversation> findByParticipantsContainingAll(String userId1, String userId2);
}