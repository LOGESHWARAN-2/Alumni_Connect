package com.example.AlumniConnect.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

// Matches login request from both AuthController.js and AuthAlumniController.js
public class LoginRequest {

    @NotBlank(message = "College email is required")
    @Email(message = "Please provide a valid email")
    private String collegeEmail;

    @NotBlank(message = "Password is required")
    private String password;

    // Getters and Setters
    public String getCollegeEmail() { return collegeEmail; }
    public void setCollegeEmail(String collegeEmail) { this.collegeEmail = collegeEmail; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}