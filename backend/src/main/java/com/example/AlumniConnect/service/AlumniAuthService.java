package com.example.AlumniConnect.service;

import com.example.AlumniConnect.dto.AlumniSignupRequest;
import com.example.AlumniConnect.dto.AuthResponse;
import com.example.AlumniConnect.dto.LoginRequest;
import com.example.AlumniConnect.model.Alumni;
import com.example.AlumniConnect.repository.AlumniRepository;
import com.example.AlumniConnect.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

// Replaces: Controllers/AuthAlumniController.js logic
@Service
public class AlumniAuthService {

    @Autowired
    private AlumniRepository alumniRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private CloudinaryService cloudinaryService;

    // signupAlumni() — same logic as signupAlumni in AuthAlumniController.js
    public AuthResponse signupAlumni(AlumniSignupRequest request, MultipartFile profilePhoto) throws Exception {
        // Check if alumni already exists
        Optional<Alumni> existingAlumni = alumniRepository.findByCollegeEmail(request.getCollegeEmail());
        if (existingAlumni.isPresent()) {
            throw new Exception("Alumni already exists, you can log in");
        }

        // Check if passwords match
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new Exception("Passwords do not match");
        }

        // Upload profile photo to Cloudinary
        String profilePhotoUrl = cloudinaryService.uploadImage(profilePhoto);

        // Create new alumni
        Alumni alumni = new Alumni();
        alumni.setFullName(request.getFullName());
        alumni.setGraduationYear(request.getGraduationYear());
        alumni.setCollegeEmail(request.getCollegeEmail());
        alumni.setPassword(passwordEncoder.encode(request.getPassword()));  // Hash password
        alumni.setLinkedin(request.getLinkedin());
        alumni.setDegreeCertificate(request.getDegreeCertificate());
        alumni.setProfilePhoto(profilePhotoUrl);
        alumni.setVerified(false);  // Mark as unverified initially
        alumni.setRole("alumni");

        alumniRepository.save(alumni);

        return new AuthResponse("Signup successful, awaiting verification", true, null, null, null, alumni.getId());
    }

    // loginAlumni() — same logic as loginAlumni in AuthAlumniController.js
    public AuthResponse loginAlumni(LoginRequest request) throws Exception {
        // Find alumni by email
        Alumni alumni = alumniRepository.findByCollegeEmail(request.getCollegeEmail())
                .orElseThrow(() -> new Exception("Invalid email or password"));

        // Verify password
        if (!passwordEncoder.matches(request.getPassword(), alumni.getPassword())) {
            throw new Exception("Invalid email or password");
        }

        // Check verification status
        if (!alumni.isVerified()) {
            throw new Exception("Account not verified. Please ask Admin to verify your account.");
        }

        // Generate JWT token
        String token = jwtTokenProvider.generateToken(alumni.getId(), true);  // true = alumni

        return new AuthResponse(
                "Login successful",
                true,
                alumni.getFullName(),
                token,
                alumni.getProfilePhoto(),
                alumni.getId()
        );
    }
}