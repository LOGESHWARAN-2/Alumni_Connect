package com.example.AlumniConnect.controller;

import com.example.AlumniConnect.dto.MessageRequest;
import com.example.AlumniConnect.model.Message;
import com.example.AlumniConnect.service.MessageService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Replaces: Routes/messageRoutes.js + Controllers/MessageController.js
@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    // POST /api/messages/send/:id — sendMessage
    @PostMapping("/send/{id}")
    public ResponseEntity<?> sendMessage(
            @PathVariable String id,
            @Valid @RequestBody MessageRequest request,
            HttpServletRequest httpRequest) {
        try {
            String senderId = (String) httpRequest.getAttribute("userId");
            boolean isAlumni = (boolean) httpRequest.getAttribute("isAlumni");

            Message message = messageService.sendMessage(senderId, id, request, isAlumni);
            return ResponseEntity.status(HttpStatus.CREATED).body(message);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(500).body(error);
        }
    }

    // GET /api/messages/:id — getMessages
    @GetMapping("/{id}")
    public ResponseEntity<?> getMessages(@PathVariable String id, HttpServletRequest httpRequest) {
        String senderId = (String) httpRequest.getAttribute("userId");
        List<Message> messages = messageService.getMessages(senderId, id);  // Changed from List<String>
        return ResponseEntity.ok(messages);
    }
}