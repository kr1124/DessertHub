package com.desserthub.comment;

import java.sql.Timestamp;

public class CommentDto {
    private String post_id;
    private String user_id;
    private String user_nn;
    private String c_body;
    private Timestamp c_writeday;

    public String getPost_id() {
        return post_id;
    }
    public void setPost_id(String post_id) {
        this.post_id = post_id;
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
    public String getC_body() {
        return c_body;
    }
    public void setC_body(String c_body) {
        this.c_body = c_body;
    }
    public Timestamp getC_writeday() {
        return c_writeday;
    }
    public void setC_writeday(Timestamp c_writeday) {
        this.c_writeday = c_writeday;
    }
}
