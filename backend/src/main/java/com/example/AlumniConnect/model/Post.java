package com.example.AlumniConnect.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

// Mapped from: Models/Post.js
@Document(collection = "posts")
public class Post {

    @Id
    private String id;

    // title: { type: String, required: true, trim: true }
    private String title;

    // content: { type: String, required: true }
    private String content;

    // author: { type: ObjectId, refPath: 'authorType', required: true }
    private String author;

    // authorType: { type: String, enum: ['User', 'Alumni'], required: true }
    private String authorType;

    // likes: [{ type: ObjectId, refPath: 'authorType' }]
    private List<String> likes = new ArrayList<>();

    // comments: [{ content, author, authorType, createdAt }] → embedded documents
    private List<Comment> comments = new ArrayList<>();

    // timestamps: true → createdAt & updatedAt
    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    // ─── Getters & Setters ───────────────────────────────────────────

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getAuthorType() { return authorType; }
    public void setAuthorType(String authorType) { this.authorType = authorType; }

    public List<String> getLikes() { return likes; }
    public void setLikes(List<String> likes) { this.likes = likes; }

    public List<Comment> getComments() { return comments; }
    public void setComments(List<Comment> comments) { this.comments = comments; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
