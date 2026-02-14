package com.example.AlumniConnect.controller;

import com.example.AlumniConnect.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Replaces: Routes/userRoutes.js + Controllers/UserController.js + UserProfileController.js
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    // GET /api/user — getUsersForSidebar
    @GetMapping
    public ResponseEntity<?> getUsersForSidebar(HttpServletRequest request) {
        String currentUserId = (String) request.getAttribute("userId");
        List<Map<String, Object>> users = userService.getUsersForSidebar(currentUserId);

        Map<String, Object> response = new HashMap<>();
        response.put("users", users);
        return ResponseEntity.ok(response);
    }

    // GET /api/user/profile — getCurrentUserProfile
    @GetMapping("/profile")
    public ResponseEntity<?> getCurrentUserProfile(HttpServletRequest request) {
        try {
            String userId = (String) request.getAttribute("userId");
            boolean isAlumni = (boolean) request.getAttribute("isAlumni");

            Object profile = userService.getCurrentUserProfile(userId, isAlumni);
            return ResponseEntity.ok(profile);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(404).body(error);
        }
    }

    // GET /api/user/:id — getUserProfile
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserProfile(@PathVariable String id) {
        try {
            Object profile = userService.getUserProfile(id);
            return ResponseEntity.ok(profile);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(404).body(error);
        }
    }

    // PUT /api/user/:id — updateUserProfile
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUserProfile(@PathVariable String id, @RequestBody Map<String, Object> updates) {
        try {
            Object updatedUser = userService.updateUserProfile(id, updates);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Profile updated successfully");
            response.put("user", updatedUser);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(404).body(error);
        }
    }
}