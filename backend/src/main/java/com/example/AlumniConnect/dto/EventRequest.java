package com.example.AlumniConnect.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

// Matches event request from eventController.js
public class EventRequest {

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Link is required")
    private String link;

    @NotNull(message = "Date and time is required")
    private LocalDateTime dateTime;

    @NotBlank(message = "Category is required")
    private String category;

    // Getters and Setters
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }

    public LocalDateTime getDateTime() { return dateTime; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}