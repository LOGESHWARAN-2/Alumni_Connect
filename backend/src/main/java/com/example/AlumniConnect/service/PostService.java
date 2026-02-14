package com.example.AlumniConnect.service;

import com.example.AlumniConnect.dto.CommentRequest;
import com.example.AlumniConnect.dto.PostRequest;
import com.example.AlumniConnect.model.Comment;
import com.example.AlumniConnect.model.Post;
import com.example.AlumniConnect.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

// Replaces: Controllers/postController.js logic
@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    // createPost() — same as createPost in postController.js
    public Post createPost(PostRequest request, String userId, boolean isAlumni) {
        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setAuthor(userId);
        post.setAuthorType(isAlumni ? "Alumni" : "User");
        return postRepository.save(post);
    }

    // getAllPosts() — same as getAllPosts in postController.js
    public List<Post> getAllPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    // addComment() — same as addComment in postController.js
    public Post addComment(String postId, CommentRequest request, String userId, boolean isAlumni) throws Exception {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new Exception("Post not found"));

        Comment comment = new Comment();
        comment.setContent(request.getContent());
        comment.setAuthor(userId);
        comment.setAuthorType(isAlumni ? "Alumni" : "User");
        comment.setCreatedAt(LocalDateTime.now());

        post.getComments().add(comment);
        return postRepository.save(post);
    }

    // toggleLike() — same as toggleLike in postController.js
    public Post toggleLike(String postId, String userId) throws Exception {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new Exception("Post not found"));

        List<String> likes = post.getLikes();

        if (likes.contains(userId)) {
            // Unlike — remove userId
            likes.remove(userId);
        } else {
            // Like — add userId
            likes.add(userId);
        }

        return postRepository.save(post);
    }
}