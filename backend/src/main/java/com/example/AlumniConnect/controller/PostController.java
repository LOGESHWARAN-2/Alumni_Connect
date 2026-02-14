package com.example.AlumniConnect.controller;

import com.example.AlumniConnect.dto.CommentRequest;
import com.example.AlumniConnect.dto.PostRequest;
import com.example.AlumniConnect.model.Post;
import com.example.AlumniConnect.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Replaces: Routes/postRoutes.js + Controllers/postController.js
@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    // POST /api/posts — createPost
    @PostMapping
    public ResponseEntity<Post> createPost(@Valid @RequestBody PostRequest request, HttpServletRequest httpRequest) {
        String userId = (String) httpRequest.getAttribute("userId");
        boolean isAlumni = (boolean) httpRequest.getAttribute("isAlumni");

        Post post = postService.createPost(request, userId, isAlumni);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    // GET /api/posts — getAllPosts
    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    // POST /api/posts/:postId/comments — addComment
    @PostMapping("/{postId}/comments")
    public ResponseEntity<?> addComment(
            @PathVariable String postId,
            @Valid @RequestBody CommentRequest request,
            HttpServletRequest httpRequest) {
        try {
            String userId = (String) httpRequest.getAttribute("userId");
            boolean isAlumni = (boolean) httpRequest.getAttribute("isAlumni");

            Post post = postService.addComment(postId, request, userId, isAlumni);
            return ResponseEntity.ok(post);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(404).body(error);
        }
    }

    // POST /api/posts/:postId/like — toggleLike
    @PostMapping("/{postId}/like")
    public ResponseEntity<?> toggleLike(@PathVariable String postId, HttpServletRequest httpRequest) {
        try {
            String userId = (String) httpRequest.getAttribute("userId");
            Post post = postService.toggleLike(postId, userId);
            return ResponseEntity.ok(post);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(404).body(error);
        }
    }
}