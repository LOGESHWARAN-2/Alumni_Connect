package com.example.AlumniConnect.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

// Mapped from: Models/users.js
@Document(collection = "users")
public class User {

    @Id
    private String id;

    // fullName: { type: String, required: true, trim: true }
    private String fullName;

    // graduationYear: { type: Number, required: true }
    private int graduationYear;

    // collegeEmail: { type: String, required: true, unique: true, match: email regex }
    @Indexed(unique = true)
    private String collegeEmail;

    // course: { type: String, required: true }
    private String course;

    // usn: { type: String, trim: true }
    private String usn;

    // fieldOfStudy: { type: String, required: true }
    private String fieldOfStudy;

    // linkedin: { type: String, match: linkedin url regex }
    private String linkedin;

    // github: { type: String, match: github url regex }
    private String github;

    // password: { type: String, required: true, minlength: 6 }
    private String password;

    // profilePhoto: { type: String }
    private String profilePhoto;

    // followingStudents: [{ type: ObjectId, ref: 'User' }]
    private List<String> followingStudents = new ArrayList<>();

    // followingAlumni: [{ type: ObjectId, ref: 'Alumni' }]
    private List<String> followingAlumni = new ArrayList<>();

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

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    public String getUsn() { return usn; }
    public void setUsn(String usn) { this.usn = usn; }

    public String getFieldOfStudy() { return fieldOfStudy; }
    public void setFieldOfStudy(String fieldOfStudy) { this.fieldOfStudy = fieldOfStudy; }

    public String getLinkedin() { return linkedin; }
    public void setLinkedin(String linkedin) { this.linkedin = linkedin; }

    public String getGithub() { return github; }
    public void setGithub(String github) { this.github = github; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getProfilePhoto() { return profilePhoto; }
    public void setProfilePhoto(String profilePhoto) { this.profilePhoto = profilePhoto; }

    public List<String> getFollowingStudents() { return followingStudents; }
    public void setFollowingStudents(List<String> followingStudents) { this.followingStudents = followingStudents; }

    public List<String> getFollowingAlumni() { return followingAlumni; }
    public void setFollowingAlumni(List<String> followingAlumni) { this.followingAlumni = followingAlumni; }

    public List<String> getFollowers() { return followers; }
    public void setFollowers(List<String> followers) { this.followers = followers; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}


