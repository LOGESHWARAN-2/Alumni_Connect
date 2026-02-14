package com.example.AlumniConnect.websocket;

import com.example.AlumniConnect.dto.MessageRequest;
import com.example.AlumniConnect.model.Message;
import com.example.AlumniConnect.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Map;

// Replaces: socket/socket.js message handling with Socket.IO
@Controller
public class ChatMessageHandler {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private MessageService messageService;

    // @MessageMapping("/chat.send") — clients send messages to /app/chat.send
    // Replaces: io.on("connection") and socket.on("sendMessage") in socket.js
    @MessageMapping("/chat.send")
    public void sendMessage(@Payload Map<String, Object> payload) {
        try {
            String senderId = (String) payload.get("senderId");
            String receiverId = (String) payload.get("receiverId");
            String messageText = (String) payload.get("message");
            boolean isAlumni = (boolean) payload.get("isAlumni");

            // Create message request
            MessageRequest request = new MessageRequest();
            request.setMessage(messageText);

            // Save message to database
            Message savedMessage = messageService.sendMessage(senderId, receiverId, request, isAlumni);

            // Send to receiver's topic — they subscribe to /topic/messages/{userId}
            messagingTemplate.convertAndSend(
                    "/topic/messages/" + receiverId,
                    savedMessage
            );

            // Also send back to sender for confirmation
            messagingTemplate.convertAndSend(
                    "/topic/messages/" + senderId,
                    savedMessage
            );

        } catch (Exception e) {
            System.err.println("Error sending message: " + e.getMessage());
        }
    }

    // For getting online users (optional — if your frontend needs this)
    @MessageMapping("/chat.online")
    @SendTo("/topic/online")
    public Map<String, Object> userOnline(@Payload Map<String, Object> payload) {
        // Broadcast user online status
        return payload;
    }
}