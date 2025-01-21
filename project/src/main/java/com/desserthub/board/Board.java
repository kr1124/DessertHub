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
    
    private String userId;
    private String userNn;
    private String boardTitle; //post_title
    private String boardContent; //post_body
    private String boardImg; //encode as base64
    private LocalDateTime boardWriteday; //post_writeday
    private int boardLiked; //좋아요 수
    private int boardComment; //댓글 수

    public Board() {
        this.boardWriteday = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getBoardTitle() {
        return boardTitle;
    }

    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
    }

    public String getBoardContent() {
        return boardContent;
    }

    public void setBoardContent(String boardContent) {
        this.boardContent = boardContent;
    }

    public String getBoardImg() {
        return boardImg;
    }

    public void setBoardImg(String boardImg) {
        this.boardImg = boardImg;
    }

    public LocalDateTime getBoardWriteday() {
        return boardWriteday;
    }

    public void setBoardWriteday(LocalDateTime boardWriteday) {
        this.boardWriteday = boardWriteday;
    }

    public int getBoardLiked() {
        return boardLiked;
    }

    public void setBoardLiked(int boardLiked) {
        this.boardLiked = boardLiked;
    }

    public int getBoardComment() {
        return boardComment;
    }

    public void setBoardComment(int boardComment) {
        this.boardComment = boardComment;
    }

    
}
