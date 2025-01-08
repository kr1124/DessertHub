package com.desserthub.like;

import java.sql.Timestamp;

public class LikeDto {
    private String post_id;
    private String user_nn;
    private String post_title;
    private Timestamp post_writeday;
    private String g_id;
    private String g_title;
    private Timestamp g_writeday;
    
    public String getPost_id() {
        return post_id;
    }
    public void setPost_id(String post_id) {
        this.post_id = post_id;
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
    public Timestamp getPost_writeday() {
        return post_writeday;
    }
    public void setPost_writeday(Timestamp post_writeday) {
        this.post_writeday = post_writeday;
    }
    public String getG_id() {
        return g_id;
    }
    public void setG_id(String g_id) {
        this.g_id = g_id;
    }
    public String getG_title() {
        return g_title;
    }
    public void setG_title(String g_title) {
        this.g_title = g_title;
    }
    public Timestamp getG_writeday() {
        return g_writeday;
    }
    public void setG_writeday(Timestamp g_writeday) {
        this.g_writeday = g_writeday;
    }
}
