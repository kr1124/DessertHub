package com.desserthub.gallery;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Gallery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String g_id;
    private String user_id;
    private String user_nn;
    private String g_title;
    private String g_hashtag;
    private String g_img;
    private int g_liked;
    private Timestamp g_writeday;

    public Gallery() {}

    public Gallery(String g_id, String user_id, String user_nn, String g_title, String g_hashtag, String g_img, int g_liked, Timestamp g_writeday){
        this.g_id = g_id;
        this.user_id = user_id;
        this.user_nn = user_nn;
        this.g_title = g_title;
        this.g_hashtag = g_hashtag;
        this.g_img = g_img;
        this.g_liked = g_liked;
        this.g_writeday = g_writeday;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getG_id() {
        return g_id;
    }

    public void setG_id(String g_id) {
        this.g_id = g_id;
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

    public String getG_title() {
        return g_title;
    }

    public void setG_title(String g_title) {
        this.g_title = g_title;
    }

    public String getG_hashtag() {
        return g_hashtag;
    }

    public void setG_hashtag(String g_hashtag) {
        this.g_hashtag = g_hashtag;
    }

    public String getG_img() {
        return g_img;
    }

    public void setG_img(String g_img) {
        this.g_img = g_img;
    }

    public int getG_liked() {
        return g_liked;
    }

    public void setG_liked(int g_liked) {
        this.g_liked = g_liked;
    }

    public Timestamp getG_writeday() {
        return g_writeday;
    }

    public void setG_writeday(Timestamp g_writeday) {
        this.g_writeday = g_writeday;
    }
    
}
