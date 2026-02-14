package com.example.AlumniConnect.model;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// Mapped from: Models/event.js
@Document(collection = "events")
public class Event {

    @Id
    private String id;

    // description: { type: String, required: true }
    private String description;

    // link: { type: String, required: true }
    private String link;

    // dateTime: { type: Date, required: true }
    private LocalDateTime dateTime;

    // category: { type: String, required: true }
    private String category;

    // ─── Getters & Setters ───────────────────────────────────────────

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }

    public LocalDateTime getDateTime() { return dateTime; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}
