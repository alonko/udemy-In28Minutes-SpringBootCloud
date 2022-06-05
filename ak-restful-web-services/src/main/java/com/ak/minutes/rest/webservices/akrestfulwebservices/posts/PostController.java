package com.ak.minutes.rest.webservices.akrestfulwebservices.posts;

import com.ak.minutes.rest.webservices.akrestfulwebservices.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class PostController {
    @Autowired
    private PostDaoService service;

    @GetMapping(path = "users/{userId}/posts")
    public List<Post> retrieveAllPostsForUser(@PathVariable int userId) {
        return service.findAllPostsForUser(userId);
    }

    @GetMapping("users/{userId}/posts/{postId}")
    public Post getPostForUser(@PathVariable int userId, @PathVariable int postId) {
        Post postForUser = service.findPostForUser(userId, postId);
        if (postForUser == null) {
            throw new UserNotFoundException("postId" + postId);
        }
        return postForUser;
    }

    @PostMapping("users/{userId}/posts/")
    public ResponseEntity<Object> createPostForUser(@PathVariable int userId, @RequestBody Post post) {
        Post savedPost = service.save(userId, post);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{postId}")
                .buildAndExpand(savedPost.getPostId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
