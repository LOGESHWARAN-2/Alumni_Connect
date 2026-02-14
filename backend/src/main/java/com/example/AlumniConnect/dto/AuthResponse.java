package com.example.AlumniConnect.dto;

// Matches the login response from AuthController.js and AuthAlumniController.js
public class AuthResponse {

    private String message;
    private boolean success;
    private String fullname;
    private String token;
    private String profilePhoto;
    private String _id;

    public AuthResponse(String message, boolean success, String fullname, String token, String profilePhoto, String _id) {
        this.message = message;
        this.success = success;
        this.fullname = fullname;
        this.token = token;
        this.profilePhoto = profilePhoto;
        this._id = _id;
    }

    // Getters and Setters
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public String getFullname() { return fullname; }
    public void setFullname(String fullname) { this.fullname = fullname; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getProfilePhoto() { return profilePhoto; }
    public void setProfilePhoto(String profilePhoto) { this.profilePhoto = profilePhoto; }

    public String get_id() { return _id; }
    public void set_id(String _id) { this._id = _id; }
}