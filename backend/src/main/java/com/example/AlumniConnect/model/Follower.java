package com.example.AlumniConnect.model;

import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

// Mapped from: Models/follower.js
@Document(collection = "followers")
public class Follower {

    @Id
    private String id;

    // user: { type: ObjectId, ref: "User" }
    private String user;

    // alumni: { type: ObjectId, ref: "Alumni" }
    private String alumni;

    // timestamps: true → createdAt & updatedAt
    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    // ─── Getters & Setters ───────────────────────────────────────────

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUser() { return user; }
    public void setUser(String user) { this.user = user; }

    public String getAlumni() { return alumni; }
    public void setAlumni(String alumni) { this.alumni = alumni; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
