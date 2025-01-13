package com.desserthub.board;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Board {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //post_id + post_num
    
    private String user_id;
    private String user_nn;
    private String title; //post_title
    private String content; //post_body
    private String post_img; //encode as base64
    private LocalDateTime createdAt; //post_writeday
    private int post_liked; //좋아요 수
    private int post_comment; //댓글 수

    public Board() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_nn() {
        return user_nn;
    }

    public void setUser_nn(String user_nn) {
        this.user_nn = user_nn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPost_img() {
        return post_img;
    }

    public void setPost_img(String post_img) {
        this.post_img = post_img;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public int getPost_liked() {
        return post_liked;
    }

    public void setPost_liked(int post_liked) {
        this.post_liked = post_liked;
    }

    public int getPost_comment() {
        return post_comment;
    }

    public void setPost_comment(int post_comment) {
        this.post_comment = post_comment;
    }

    // getters and setters
    
}
