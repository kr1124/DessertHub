package com.desserthub.gallery;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Gallery {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long galleryId;

    private String userId;
    private String userNn;
    private String galleryTitle;
    private String galleryHashtag;
    private String galleryImg;
    private int galleryLiked;
    private LocalDateTime galleryWriteday;

    public Gallery() {
        this.galleryWriteday = LocalDateTime.now();
    }

    public Long getGalleryId() {
        return galleryId;
    }
    public void setGalleryId(Long galleryId) {
        this.galleryId = galleryId;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUserNn() {
        return userNn;
    }
    public void setUserNn(String userNn) {
        this.userNn = userNn;
    }
    public String getGalleryTitle() {
        return galleryTitle;
    }
    public void setGalleryTitle(String galleryTitle) {
        this.galleryTitle = galleryTitle;
    }
    public String getGalleryHashtag() {
        return galleryHashtag;
    }
    public void setGalleryHashtag(String galleryHashtag) {
        this.galleryHashtag = galleryHashtag;
    }
    public String getGalleryImg() {
        return galleryImg;
    }
    public void setGalleryImg(String galleryImg) {
        this.galleryImg = galleryImg;
    }
    public int getGalleryLiked() {
        return galleryLiked;
    }
    public void setGalleryLiked(int galleryLiked) {
        this.galleryLiked = galleryLiked;
    }
    public LocalDateTime getGalleryWriteday() {
        return galleryWriteday;
    }
    public void setGalleryWriteday(LocalDateTime galleryWriteday) {
        this.galleryWriteday = galleryWriteday;
    }

    public void set_now() {
        this.galleryWriteday = LocalDateTime.now();
    }
}
