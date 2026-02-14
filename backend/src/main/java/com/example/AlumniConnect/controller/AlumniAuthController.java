package com.example.AlumniConnect.controller;

import com.example.AlumniConnect.dto.AlumniSignupRequest;
import com.example.AlumniConnect.dto.AuthResponse;
import com.example.AlumniConnect.dto.LoginRequest;
import com.example.AlumniConnect.service.AlumniAuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

// Replaces: Routes/AuthAlumniRoutes.js + Controllers/AuthAlumniController.js
@RestController
@RequestMapping("/api/alumni")
public class AlumniAuthController {

    @Autowired
    private AlumniAuthService alumniAuthService;

    // POST /api/alumni/signup — same as signup route in AuthAlumniRoutes.js
    @PostMapping("/signup")
    public ResponseEntity<?> signupAlumni(
            @RequestParam("image") MultipartFile image,
            @Valid @ModelAttribute AlumniSignupRequest request) {
        try {
            AuthResponse response = alumniAuthService.signupAlumni(request, image);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("message", e.getMessage());
            error.put("success", false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    // POST /api/alumni/login — same as login route in AuthAlumniRoutes.js
    @PostMapping("/login")
    public ResponseEntity<?> loginAlumni(@Valid @RequestBody LoginRequest request) {
        try {
            AuthResponse response = alumniAuthService.loginAlumni(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("message", e.getMessage());
            error.put("success", false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }
}