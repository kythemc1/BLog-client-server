package com.example.newsblog.client.model;

public class Post {
    private int postId;
    private String content;
    private String title;
    private String imageUrl;
    private String videoUrl;
    private int UserId;



    public int getPostId() {
        return postId;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public int getUserId() {
        return UserId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }
}
