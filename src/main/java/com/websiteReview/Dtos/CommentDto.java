package com.websiteReview.Dtos;

import java.time.LocalDateTime;
import java.util.Date;

public class CommentDto {

    private int commentId;

    private String description;

    private LocalDateTime date;

    private int userId;

    private int questionId;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CommentDto() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public CommentDto(int commentId, String description, LocalDateTime date, int userId, int questionId) {
        this.commentId = commentId;
        this.description = description;
        this.date = date;
        this.userId = userId;
        this.questionId = questionId;
    }

    @Override
    public String toString() {
        return "CommentDto [commentId=" + commentId + ", description=" + description + ", date=" + date + ", userId="
                + userId + ", questionId=" + questionId + "]";
    }

}
