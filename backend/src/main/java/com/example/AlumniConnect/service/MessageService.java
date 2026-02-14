package com.example.AlumniConnect.service;

import com.example.AlumniConnect.dto.MessageRequest;
import com.example.AlumniConnect.model.Alumni;
import com.example.AlumniConnect.model.Conversation;
import com.example.AlumniConnect.model.Message;
import com.example.AlumniConnect.model.User;
import com.example.AlumniConnect.repository.AlumniRepository;
import com.example.AlumniConnect.repository.ConversationRepository;
import com.example.AlumniConnect.repository.MessageRepository;
import com.example.AlumniConnect.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

// Replaces: Controllers/MessageController.js logic
@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AlumniRepository alumniRepository;

    // sendMessage() — same as sendMessage in MessageController.js
    public Message sendMessage(String senderId, String receiverId, MessageRequest request, boolean isAlumni) throws Exception {
        // Find receiver (could be User or Alumni)
        User receiverUser = userRepository.findById(receiverId).orElse(null);
        Alumni receiverAlumni = null;
        if (receiverUser == null) {
            receiverAlumni = alumniRepository.findById(receiverId).orElse(null);
        }

        if (receiverUser == null && receiverAlumni == null) {
            throw new Exception("Receiver not found");
        }

        String receiverType = receiverUser != null ? "User" : "Alumni";

        // Find or create conversation
        Conversation conversation = conversationRepository
                .findByParticipantsContainingAll(senderId, receiverId)
                .orElse(null);

        if (conversation == null) {
            conversation = new Conversation();
            conversation.setParticipants(Arrays.asList(senderId, receiverId));
            conversation = conversationRepository.save(conversation);
        }

        // Create message
        Message message = new Message();
        message.setSenderId(senderId);
        message.setSenderType(isAlumni ? "Alumni" : "User");
        message.setReceiverId(receiverId);
        message.setReceiverType(receiverType);
        message.setMessage(request.getMessage());
        message.setCreatedAt(LocalDateTime.now());

        Message savedMessage = messageRepository.save(message);

        // Add message to conversation
        conversation.getMessages().add(savedMessage.getId());
        conversationRepository.save(conversation);

        return savedMessage;
    }

    // getMessages() — returns actual Message objects, not just IDs
    public List<Message> getMessages(String senderId, String userToChatId) {
        // Find conversation
        Conversation conversation = conversationRepository
                .findByParticipantsContainingAll(senderId, userToChatId)
                .orElse(null);

        if (conversation == null) {
            return List.of();  // Return empty list if no conversation
        }

        // Get all message IDs
        List<String> messageIds = conversation.getMessages();

        // Fetch actual Message objects from database
        return messageRepository.findAllById(messageIds);
    }
}