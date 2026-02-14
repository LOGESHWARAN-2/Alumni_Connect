package com.example.AlumniConnect.security;

import com.example.AlumniConnect.model.Alumni;
import com.example.AlumniConnect.model.User;
import com.example.AlumniConnect.repository.AlumniRepository;
import com.example.AlumniConnect.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

// Replaces: The dual-model lookup in ProtectRoute (checks both User and Alumni)
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AlumniRepository alumniRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        // First try User collection
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getId(),
                    user.getPassword(),
                    new ArrayList<>()
            );
        }

        // Then try Alumni collection
        Alumni alumni = alumniRepository.findById(userId).orElse(null);
        if (alumni != null) {
            return new org.springframework.security.core.userdetails.User(
                    alumni.getId(),
                    alumni.getPassword(),
                    new ArrayList<>()
            );
        }

        throw new UsernameNotFoundException("User not found with id: " + userId);
    }
}