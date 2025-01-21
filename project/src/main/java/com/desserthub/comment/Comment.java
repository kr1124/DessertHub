package com.desserthub.comment;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private String userNn;
    private Long boardId;
    private String commentBody;
    private LocalDateTime commentTime;

    public Comment() {
        this.commentTime = LocalDateTime.now();
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

    
    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }
    
    
    public String getCommentBody() {
        return commentBody;
    }
    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }
    public LocalDateTime getCommentTime() {
        return commentTime;
    }
    public void setCommentTime(LocalDateTime commentTime) {
        this.commentTime = commentTime;
    }

    

}