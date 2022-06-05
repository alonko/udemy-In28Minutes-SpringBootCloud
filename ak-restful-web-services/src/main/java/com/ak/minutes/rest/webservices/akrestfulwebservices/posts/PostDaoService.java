package com.ak.minutes.rest.webservices.akrestfulwebservices.posts;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PostDaoService {
    // Map of user id and list of posts
    private static Map<Integer, List<Post>> posts = new HashMap<>();
    private static int postsCount = 3;

    static {
        posts.put(1, List.of(new Post(1, 1, "Gold")));
        posts.put(2, Arrays.asList(new Post(2, 2, "item1"), new Post(3, 2, "item2")));
        posts.put(3, null);
    }

    public List<Post> findAllPostsForUser(int userId) {
        return posts.get(userId);
    }

    public Post save(int userId, Post post) {
        if (post.getPostId() == null) {
            post.setPostId(++postsCount);
        }
        post.setUserId(userId);
        List<Post> existingPosts = posts.computeIfAbsent(userId, k -> new ArrayList<>());
        posts.put(userId, append(existingPosts, post));
        return post;
    }

    private static <T> List<T> append(final List<T> al, final T newValue) {
        final List<T> bl = new ArrayList<>(al);
        Collections.addAll(bl, newValue);
        return List.copyOf(bl);
    }

    public Post findPostForUser(int userId, int postId) {
        List<Post> existingPosts = posts.get(userId);
        for (Post post : existingPosts) {
            if (post.getPostId() == postId)
                return post;
        }

        return null;
    }
}