package com.example.AlumniConnect.controller;

import com.example.AlumniConnect.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

// Replaces: Routes/routeUpload.js
@RestController
@RequestMapping("/api/upload")
public class UploadController {

    @Autowired
    private CloudinaryService cloudinaryService;

    // POST /api/upload/student — upload student profile photo
    @PostMapping("/student")
    public ResponseEntity<?> uploadStudentPhoto(@RequestParam("profilePhoto") MultipartFile file) {
        try {
            String url = cloudinaryService.uploadImage(file);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Uploaded!");

            Map<String, String> data = new HashMap<>();
            data.put("secure_url", url);
            response.put("data", data);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", "Error uploading image");
            return ResponseEntity.status(500).body(error);
        }
    }

    // POST /api/upload/alumni — upload alumni profile photo
    @PostMapping("/alumni")
    public ResponseEntity<?> uploadAlumniPhoto(@RequestParam("profilePhoto") MultipartFile file) {
        try {
            String url = cloudinaryService.uploadImage(file);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Uploaded!");

            Map<String, String> data = new HashMap<>();
            data.put("secure_url", url);
            response.put("data", data);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", "Error uploading image");
            return ResponseEntity.status(500).body(error);
        }
    }
}