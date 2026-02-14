package com.example.AlumniConnect.service;

import com.example.AlumniConnect.model.Alumni;
import com.example.AlumniConnect.model.User;
import com.example.AlumniConnect.repository.AlumniRepository;
import com.example.AlumniConnect.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Replaces: Controllers/FollowController.js logic
@Service
public class FollowService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AlumniRepository alumniRepository;

    // followUser() — same as followUser in FollowController.js
    public void followUser(String userId, String followeeId) throws Exception {
        // Find the current user (could be User or Alumni)
        User user = userRepository.findById(userId).orElse(null);
        Alumni alumniUser = null;
        if (user == null) {
            alumniUser = alumniRepository.findById(userId).orElse(null);
        }

        // Find the followee (must be a User)
        User followee = userRepository.findById(followeeId)
                .orElseThrow(() -> new Exception("User not found"));

        if (user == null && alumniUser == null) {
            throw new Exception("Current user not found");
        }

        // Add followeeId to current user's followingStudents
        if (user != null) {
            if (!user.getFollowingStudents().contains(followeeId)) {
                user.getFollowingStudents().add(followeeId);
                userRepository.save(user);
            }
        } else {
            if (!alumniUser.getFollowers().contains(followeeId)) {
                // Alumni doesn't have followingStudents, just track in User's followers
            }
        }

        // Add userId to followee's followers
        if (!followee.getFollowers().contains(userId)) {
            followee.getFollowers().add(userId);
            userRepository.save(followee);
        }
    }

    // followAlumni() — same as followAlumni in FollowController.js
    public void followAlumni(String userId, String alumniId) throws Exception {
        // Find the current user (could be User or Alumni)
        User user = userRepository.findById(userId).orElse(null);
        Alumni alumniUser = null;
        if (user == null) {
            alumniUser = alumniRepository.findById(userId).orElse(null);
        }

        // Find the alumni to follow
        Alumni alumni = alumniRepository.findById(alumniId)
                .orElseThrow(() -> new Exception("Alumni not found"));

        if (user == null && alumniUser == null) {
            throw new Exception("Current user not found");
        }

        // Add alumniId to current user's followingAlumni
        if (user != null) {
            if (!user.getFollowingAlumni().contains(alumniId)) {
                user.getFollowingAlumni().add(alumniId);
                userRepository.save(user);
            }
        }

        // Add userId to alumni's followers
        if (!alumni.getFollowers().contains(userId)) {
            alumni.getFollowers().add(userId);
            alumniRepository.save(alumni);
        }
    }

    // unfollowUser() — same as unfollowUser in FollowController.js
    public void unfollowUser(String userId, String followeeId) throws Exception {
        User user = userRepository.findById(userId).orElse(null);
        Alumni alumniUser = null;
        if (user == null) {
            alumniUser = alumniRepository.findById(userId).orElse(null);
        }

        User followee = userRepository.findById(followeeId)
                .orElseThrow(() -> new Exception("User not found"));

        if (user == null && alumniUser == null) {
            throw new Exception("Current user not found");
        }

        // Remove followeeId from current user's followingStudents
        if (user != null) {
            user.getFollowingStudents().remove(followeeId);
            userRepository.save(user);
        }

        // Remove userId from followee's followers
        followee.getFollowers().remove(userId);
        userRepository.save(followee);
    }

    // unfollowAlumni() — same as unfollowAlumni in FollowController.js
    public void unfollowAlumni(String userId, String alumniId) throws Exception {
        User user = userRepository.findById(userId).orElse(null);

        Alumni alumni = alumniRepository.findById(alumniId)
                .orElseThrow(() -> new Exception("Alumni not found"));

        if (user == null) {
            throw new Exception("Current user not found");
        }

        // Remove alumniId from user's followingAlumni
        user.getFollowingAlumni().remove(alumniId);
        userRepository.save(user);

        // Remove userId from alumni's followers
        alumni.getFollowers().remove(userId);
        alumniRepository.save(alumni);
    }
}