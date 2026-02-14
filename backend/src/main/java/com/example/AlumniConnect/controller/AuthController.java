package com.example.AlumniConnect.controller;

import com.example.AlumniConnect.dto.AuthResponse;
import com.example.AlumniConnect.dto.LoginRequest;
import com.example.AlumniConnect.dto.SignupRequest;
import com.example.AlumniConnect.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

// Replaces: Routes/AuthRouter.js + Controllers/AuthController.js
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    // POST /api/auth/signup — same as signup route in AuthRouter.js
    @PostMapping("/signup")
    public ResponseEntity<?> signup(
            @RequestParam("image") MultipartFile image,
            @Valid @ModelAttribute SignupRequest request) {
        try {
            AuthResponse response = authService.signup(request, image);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("message", e.getMessage());
            error.put("success", false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    // POST /api/auth/login — same as login route in AuthRouter.js
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        try {
            AuthResponse response = authService.login(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("message", e.getMessage());
            error.put("success", false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    // GET /api/auth/verify — same as verify route in AuthRouter.js
    @GetMapping("/verify")
    public ResponseEntity<?> verify() {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Token is valid");
        return ResponseEntity.ok(response);
    }
}