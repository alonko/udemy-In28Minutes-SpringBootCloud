package com.ak.minutes.rest.webservices.akrestfulwebservices.posts;

import com.ak.minutes.rest.webservices.akrestfulwebservices.exception.UserNotFoundException;
import com.ak.minutes.rest.webservices.akrestfulwebservices.user.User;
import com.ak.minutes.rest.webservices.akrestfulwebservices.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class PostController {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;


    @GetMapping(path = "users/{userId}/posts")
    public List<Post> retrieveAllPostsForUser(@PathVariable int userId) {
        User user = getUser(userId);
        return user.getPosts();
    }

    private User getUser(int userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new UserNotFoundException("id-" + userId);
        }
        return user.get();
    }

    @GetMapping("users/{userId}/posts/{postId}")
    public Post getPostForUser(@PathVariable int userId, @PathVariable int postId) {
//        Post postForUser = service.findPostForUser(userId, postId);
//        if (postForUser == null) {
//            throw new UserNotFoundException("postId" + postId);
//        }
//        return postForUser;

        return null;
    }

    @PostMapping("users/{userId}/posts/")
    public ResponseEntity<Object> createPostForUser(@PathVariable int userId, @RequestBody Post post) {
        User user = getUser(userId);
        post.setUser(user);
        postRepository.save(post);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{postId}")
                .buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
