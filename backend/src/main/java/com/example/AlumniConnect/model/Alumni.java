package com.example.AlumniConnect.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

// Mapped from: Models/alumni.js
@Document(collection = "alumni")
public class Alumni {

    @Id
    private String id;

    // fullName: { type: String, required: true, trim: true }
    private String fullName;

    // graduationYear: { type: Number, required: true }
    private int graduationYear;

    // collegeEmail: { type: String, required: true, unique: true, match: email regex }
    @Indexed(unique = true)
    private String collegeEmail;

    // password: { type: String, required: true, minlength: 6 }
    private String password;

    // linkedin: { type: String, match: linkedin url regex }
    private String linkedin;

    // degreeCertificate: { type: String, required: true }
    private String degreeCertificate;

    // profilePhoto: { type: String, required: true }
    private String profilePhoto;

    // verified: { type: Boolean, default: false }
    private boolean verified = false;

    // role: { type: String, enum: ['alumni'], required: true }
    private String role = "alumni";

    // followers: [{ type: ObjectId, ref: 'User' }]
    private List<String> followers = new ArrayList<>();

    // timestamps: true → createdAt & updatedAt
    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    // ─── Getters & Setters ───────────────────────────────────────────

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public int getGraduationYear() { return graduationYear; }
    public void setGraduationYear(int graduationYear) { this.graduationYear = graduationYear; }

    public String getCollegeEmail() { return collegeEmail; }
    public void setCollegeEmail(String collegeEmail) { this.collegeEmail = collegeEmail; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getLinkedin() { return linkedin; }
    public void setLinkedin(String linkedin) { this.linkedin = linkedin; }

    public String getDegreeCertificate() { return degreeCertificate; }
    public void setDegreeCertificate(String degreeCertificate) { this.degreeCertificate = degreeCertificate; }

    public String getProfilePhoto() { return profilePhoto; }
    public void setProfilePhoto(String profilePhoto) { this.profilePhoto = profilePhoto; }

    public boolean isVerified() { return verified; }
    public void setVerified(boolean verified) { this.verified = verified; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public List<String> getFollowers() { return followers; }
    public void setFollowers(List<String> followers) { this.followers = followers; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
