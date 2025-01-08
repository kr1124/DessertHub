package com.desserthub.board;

import java.sql.Timestamp;

public class BoardDto {
    private String post_id;
    private int post_num;
    private String user_id;
    private String user_nn;
    private String post_title;
    private String post_body;
    private String post_img; //encode as base64
    private int post_liked;
    private int post_comment;
    private Timestamp post_writeday; // 일단 java.sql.Timestamp를 사용했지만 향후 달라질 수 있음
    // TODO

    public String getPost_id() {
        return post_id;
    }
    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }
    public int getPost_num() {
        return post_num;
    }
    public void setPost_num(int post_num) {
        this.post_num = post_num;
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
    public String getPost_title() {
        return post_title;
    }
    public void setPost_title(String post_title) {
        this.post_title = post_title;
    }
    public String getPost_body() {
        return post_body;
    }
    public void setPost_body(String post_body) {
        this.post_body = post_body;
    }
    public String getPost_img() {
        return post_img;
    }
    public void setPost_img(String post_img) {
        this.post_img = post_img;
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
    public Timestamp getPost_writeday() {
        return post_writeday;
    }
    public void setPost_writeday(Timestamp post_writeday) {
        this.post_writeday = post_writeday;
    }

    
}
