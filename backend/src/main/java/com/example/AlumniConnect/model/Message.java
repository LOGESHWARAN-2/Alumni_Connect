package com.example.AlumniConnect.model;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// Mapped from: Models/message.js
@Document(collection = "messages")
public class Message {

    @Id
    private String id;

    // senderId: { type: ObjectId, refPath: 'senderType', required: true }
    private String senderId;

    // senderType: { type: String, enum: ['User', 'Alumni'], required: true }
    private String senderType;

    // receiverId: { type: ObjectId, refPath: 'receiverType', required: true }
    private String receiverId;

    // receiverType: { type: String, enum: ['User', 'Alumni'], required: true }
    private String receiverType;

    // message: { type: String, required: true }
    private String message;

    // createdAt: { type: Date, default: Date.now }
    private LocalDateTime createdAt = LocalDateTime.now();

    // ─── Getters & Setters ───────────────────────────────────────────

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getSenderId() { return senderId; }
    public void setSenderId(String senderId) { this.senderId = senderId; }

    public String getSenderType() { return senderType; }
    public void setSenderType(String senderType) { this.senderType = senderType; }

    public String getReceiverId() { return receiverId; }
    public void setReceiverId(String receiverId) { this.receiverId = receiverId; }

    public String getReceiverType() { return receiverType; }
    public void setReceiverType(String receiverType) { this.receiverType = receiverType; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}