package com.example.AlumniConnect.controller;

import com.example.AlumniConnect.model.Alumni;
import com.example.AlumniConnect.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Replaces: Routes/AdminRoutes.js + Controllers/AdminController.js
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // GET /admin/alumni — same as getAllAlumni route in AdminRoutes.js
    @GetMapping("/alumni")
    public ResponseEntity<?> getAllAlumni() {
        List<Alumni> alumni = adminService.getAllAlumni();
        Map<String, Object> response = new HashMap<>();
        response.put("alumni", alumni);
        return ResponseEntity.ok(response);
    }

    // PUT /admin/alumni/:id/verify — same as verifyAlumni route in AdminRoutes.js
    @PutMapping("/alumni/{id}/verify")
    public ResponseEntity<?> verifyAlumni(@PathVariable String id) {
        try {
            Alumni alumni = adminService.verifyAlumni(id);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Alumni verified successfully");
            response.put("alumni", alumni);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(404).body(error);
        }
    }
}