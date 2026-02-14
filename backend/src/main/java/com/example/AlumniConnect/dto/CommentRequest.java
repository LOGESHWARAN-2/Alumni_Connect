package com.example.AlumniConnect.dto;

import jakarta.validation.constraints.NotBlank;

// Matches comment request from postController.js
public class CommentRequest {

    @NotBlank(message = "Comment content is required")
    private String content;

    // Getters and Setters
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}