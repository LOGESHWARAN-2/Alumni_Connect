package com.example.AlumniConnect.model;

import java.time.LocalDateTime;

// Mapped from: the embedded comments[] array inside Models/Post.js
// This is NOT a separate collection — it's embedded inside Post documents
public class Comment {

    // comments[].content
    private String content;

    // comments[].author → ObjectId ref
    private String author;

    // comments[].authorType → enum ['User', 'Alumni']
    private String authorType;

    // comments[].createdAt: { type: Date, default: Date.now }
    private LocalDateTime createdAt = LocalDateTime.now();

    // ─── Getters & Setters ───────────────────────────────────────────

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getAuthorType() { return authorType; }
    public void setAuthorType(String authorType) { this.authorType = authorType; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
