package com.example.AlumniConnect.controller;

import com.example.AlumniConnect.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

// Replaces: Routes/FollowRoutes.js + Controllers/FollowController.js
@RestController
@RequestMapping("/api/follow")
public class FollowController {

    @Autowired
    private FollowService followService;

    // POST /api/follow/:userId/follow/user/:followeeId — followUser
    @PostMapping("/{userId}/follow/user/{followeeId}")
    public ResponseEntity<?> followUser(@PathVariable String userId, @PathVariable String followeeId) {
        try {
            followService.followUser(userId, followeeId);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Successfully followed the user");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(500).body(error);
        }
    }

    // POST /api/follow/:userId/follow/alumni/:alumniId — followAlumni
    @PostMapping("/{userId}/follow/alumni/{alumniId}")
    public ResponseEntity<?> followAlumni(@PathVariable String userId, @PathVariable String alumniId) {
        try {
            followService.followAlumni(userId, alumniId);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Successfully followed the alumni");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(500).body(error);
        }
    }

    // POST /api/follow/:userId/unfollow/user/:followeeId — unfollowUser
    @PostMapping("/{userId}/unfollow/user/{followeeId}")
    public ResponseEntity<?> unfollowUser(@PathVariable String userId, @PathVariable String followeeId) {
        try {
            followService.unfollowUser(userId, followeeId);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Successfully unfollowed the user");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(500).body(error);
        }
    }

    // POST /api/follow/:userId/unfollow/alumni/:alumniId — unfollowAlumni
    @PostMapping("/{userId}/unfollow/alumni/{alumniId}")
    public ResponseEntity<?> unfollowAlumni(@PathVariable String userId, @PathVariable String alumniId) {
        try {
            followService.unfollowAlumni(userId, alumniId);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Successfully unfollowed the alumni");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(500).body(error);
        }
    }
}