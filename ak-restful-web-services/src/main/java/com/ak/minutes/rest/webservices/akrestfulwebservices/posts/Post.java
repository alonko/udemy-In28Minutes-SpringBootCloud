package com.ak.minutes.rest.webservices.akrestfulwebservices.posts;

public class Post {
    private Integer postId;
    private Integer userId;
    private String description;

    public Post(Integer postId, Integer userId, String description) {
        this.postId = postId;
        this.userId = userId;
        this.description = description;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", userId=" + userId +
                ", description='" + description + '\'' +
                '}';
    }
}
