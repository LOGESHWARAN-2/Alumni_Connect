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

// Replaces: Controllers/NetworkController.js logic
@Service
public class NetworkService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AlumniRepository alumniRepository;

    // getNetworkData() — same as getNetworkData in NetworkController.js
    public Map<String, Object> getNetworkData() {
        List<Map<String, Object>> students = new ArrayList<>();
        List<Map<String, Object>> alumni = new ArrayList<>();

        // Get all students
        List<User> allStudents = userRepository.findAll();
        for (User student : allStudents) {
            Map<String, Object> studentMap = new HashMap<>();
            studentMap.put("_id", student.getId());
            studentMap.put("name", student.getFullName());
            studentMap.put("graduationYear", student.getGraduationYear());
            studentMap.put("field", student.getFieldOfStudy());
            studentMap.put("linkedin", student.getLinkedin());
            studentMap.put("followers", student.getFollowers());
            students.add(studentMap);
        }

        // Get all alumni
        List<Alumni> allAlumni = alumniRepository.findAll();
        for (Alumni alum : allAlumni) {
            Map<String, Object> alumniMap = new HashMap<>();
            alumniMap.put("_id", alum.getId());
            alumniMap.put("name", alum.getFullName());
            alumniMap.put("graduationYear", alum.getGraduationYear());
            alumniMap.put("field", "N/A");
            alumniMap.put("position", alum.getRole());
            alumniMap.put("linkedin", alum.getLinkedin());
            alumniMap.put("followers", alum.getFollowers());
            alumni.add(alumniMap);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("students", students);
        result.put("alumni", alumni);
        return result;
    }
}