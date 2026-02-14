package com.example.AlumniConnect.service;

import com.example.AlumniConnect.dto.AuthResponse;
import com.example.AlumniConnect.dto.LoginRequest;
import com.example.AlumniConnect.dto.SignupRequest;
import com.example.AlumniConnect.model.User;
import com.example.AlumniConnect.repository.UserRepository;
import com.example.AlumniConnect.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

// Replaces: Controllers/AuthController.js logic
@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private CloudinaryService cloudinaryService;

    // signup() — same logic as signup in AuthController.js
    public AuthResponse signup(SignupRequest request, MultipartFile profilePhoto) throws Exception {
        // Check if user already exists
        Optional<User> existingUser = userRepository.findByCollegeEmail(request.getCollegeEmail());
        if (existingUser.isPresent()) {
            throw new Exception("User already exists, you can log in");
        }

        // Check if passwords match
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new Exception("Passwords do not match");
        }

        // Upload profile photo to Cloudinary
        String profilePhotoUrl = cloudinaryService.uploadImage(profilePhoto);

        // Create new user
        User user = new User();
        user.setFullName(request.getFullName());
        user.setGraduationYear(request.getGraduationYear());
        user.setCollegeEmail(request.getCollegeEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));  // Hash password
        user.setCourse(request.getCourse());
        user.setUsn(request.getUsn());
        user.setFieldOfStudy(request.getFieldOfStudy());
        user.setLinkedin(request.getLinkedin());
        user.setGithub(request.getGithub());
        user.setProfilePhoto(profilePhotoUrl);

        userRepository.save(user);

        return new AuthResponse("Signup successful", true, null, null, null, user.getId());
    }

    // login() — same logic as login in AuthController.js
    public AuthResponse login(LoginRequest request) throws Exception {
        // Find user by email
        User user = userRepository.findByCollegeEmail(request.getCollegeEmail())
                .orElseThrow(() -> new Exception("Invalid email or password"));

        // Verify password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new Exception("Invalid email or password");
        }

        // Generate JWT token
        String token = jwtTokenProvider.generateToken(user.getId(), false);  // false = not alumni

        return new AuthResponse(
                "Login successful",
                true,
                user.getFullName(),
                token,
                user.getProfilePhoto(),
                user.getId()
        );
    }
}