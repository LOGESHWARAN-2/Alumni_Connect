package com.example.AlumniConnect.config;

import com.example.AlumniConnect.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// Replaces: All the app.use() middleware setup in server.js
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Disable CSRF for REST API
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Public endpoints (no JWT required) — same as routes without protectRoute
                        .requestMatchers("/api/auth/**").permitAll()           // signup, login
                        .requestMatchers("/api/alumni/**").permitAll()         // alumni signup, login
                        .requestMatchers("/api/events/**").permitAll()         // events CRUD
                        .requestMatchers("/api/upload/**").permitAll()         // image upload
                        .requestMatchers("/").permitAll()                      // health check

                        // Swagger UI endpoints — allow public access
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/v3/api-docs/**").permitAll()
                        .requestMatchers("/swagger-ui.html").permitAll()

                        // Protected endpoints (JWT required) — same as routes with protectRoute or verifyToken
                        .requestMatchers("/api/posts/**").authenticated()      // posts need auth
                        .requestMatchers("/api/follow/**").authenticated()     // follow/unfollow need auth
                        .requestMatchers("/api/user/**").authenticated()       // user profile needs auth
                        .requestMatchers("/api/network/**").authenticated()    // network data needs auth
                        .requestMatchers("/api/messages/**").authenticated()   // messages need auth
                        .requestMatchers("/admin/**").authenticated()          // admin routes need auth

                        .anyRequest().authenticated()
                );

        // Add JWT filter before Spring Security's default filter
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Replaces bcrypt.hash and bcrypt.compare
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}