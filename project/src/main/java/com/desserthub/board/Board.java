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
    private Long id; // post_id + post_num

    private Long userId;
    private String userNn;
    private String boardCategory; // 종류
    private String boardTitle; // post_title
    private String boardContent; // post_body
    private String boardImg; // encode as base64
    private LocalDateTime boardWriteday; // post_writeday
    private int boardView; // 조회수
    private int boardLiked; // 좋아요 수
    private int boardComment; // 댓글 수

    public Board() {
        this.boardWriteday = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserNn() {
        return userNn;
    }

    public void setUserNn(String userNn) {
        this.userNn = userNn;
    }

    public String getBoardCategory() {
        return boardCategory;
    }

    public void setBoardCategory(String boardCategory) {
        this.boardCategory = boardCategory;
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

    public int getBoardView() {
        return boardView;
    }

    public void setBoardView(int boardView) {
        this.boardView = boardView;
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
