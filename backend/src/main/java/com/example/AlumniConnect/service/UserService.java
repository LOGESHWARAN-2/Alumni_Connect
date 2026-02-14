package com.example.AlumniConnect.service;

import com.example.AlumniConnect.model.Alumni;
import com.example.AlumniConnect.model.User;
import com.example.AlumniConnect.repository.AlumniRepository;
import com.example.AlumniConnect.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Replaces: Controllers/UserController.js and UserProfileController.js logic
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AlumniRepository alumniRepository;

    // getUsersForSidebar() — same as getUsersForSidebar in UserController.js
    public List<Map<String, Object>> getUsersForSidebar(String currentUserId) {
        List<Map<String, Object>> users = new ArrayList<>();

        // Get all students except current user
        List<User> students = userRepository.findAll();
        for (User student : students) {
            if (!student.getId().equals(currentUserId)) {
                Map<String, Object> userMap = new HashMap<>();
                userMap.put("_id", student.getId());
                userMap.put("fullName", student.getFullName());
                userMap.put("profilePhoto", student.getProfilePhoto());
                users.add(userMap);
            }
        }

        // Get all alumni except current user
        List<Alumni> alumniList = alumniRepository.findAll();
        for (Alumni alumni : alumniList) {
            if (!alumni.getId().equals(currentUserId)) {
                Map<String, Object> alumniMap = new HashMap<>();
                alumniMap.put("_id", alumni.getId());
                alumniMap.put("fullName", alumni.getFullName());
                alumniMap.put("profilePhoto", alumni.getProfilePhoto());
                users.add(alumniMap);
            }
        }

        return users;
    }

    // getCurrentUserProfile() — same as getCurrentUserProfile in UserProfileController.js
    public Object getCurrentUserProfile(String userId, boolean isAlumni) throws Exception {
        if (isAlumni) {
            return alumniRepository.findById(userId)
                    .orElseThrow(() -> new Exception("User not found"));
        } else {
            return userRepository.findById(userId)
                    .orElseThrow(() -> new Exception("User not found"));
        }
    }

    // getUserProfile() — same as getUserProfile in UserProfileController.js
    public Object getUserProfile(String id) throws Exception {
        // Check User first
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            return user;
        }

        // Then check Alumni
        Alumni alumni = alumniRepository.findById(id).orElse(null);
        if (alumni != null) {
            return alumni;
        }

        throw new Exception("User not found");
    }

    // updateUserProfile() — same as updateUserProfile in UserProfileController.js
    public Object updateUserProfile(String id, Map<String, Object> updates) throws Exception {
        // Try User first
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            // Apply updates (you can customize which fields to update)
            if (updates.containsKey("fullName")) user.setFullName((String) updates.get("fullName"));
            if (updates.containsKey("linkedin")) user.setLinkedin((String) updates.get("linkedin"));
            if (updates.containsKey("github")) user.setGithub((String) updates.get("github"));
            return userRepository.save(user);
        }

        // Try Alumni
        Alumni alumni = alumniRepository.findById(id).orElse(null);
        if (alumni != null) {
            if (updates.containsKey("fullName")) alumni.setFullName((String) updates.get("fullName"));
            if (updates.containsKey("linkedin")) alumni.setLinkedin((String) updates.get("linkedin"));
            return alumniRepository.save(alumni);
        }

        throw new Exception("User not found");
    }
}