package com.example.AlumniConnect.service;

import com.example.AlumniConnect.model.Alumni;
import com.example.AlumniConnect.repository.AlumniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Replaces: Controllers/AdminController.js logic
@Service
public class AdminService {

    @Autowired
    private AlumniRepository alumniRepository;

    // getAllAlumni() — same as getAllAlumni in AdminController.js
    public List<Alumni> getAllAlumni() {
        return alumniRepository.findAll();
    }

    // verifyAlumni() — same as verifyAlumni in AdminController.js
    public Alumni verifyAlumni(String id) throws Exception {
        Alumni alumni = alumniRepository.findById(id)
                .orElseThrow(() -> new Exception("Alumni not found"));

        alumni.setVerified(true);
        return alumniRepository.save(alumni);
    }
}