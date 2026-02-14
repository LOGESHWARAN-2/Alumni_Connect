package com.example.AlumniConnect.dto;

import jakarta.validation.constraints.NotBlank;

// Matches message request from MessageController.js
public class MessageRequest {

    @NotBlank(message = "Message is required")
    private String message;

    // Getters and Setters
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}