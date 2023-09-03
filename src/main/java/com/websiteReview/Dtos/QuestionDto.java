package com.websiteReview.Dtos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuestionDto {

    private int questionId;

    private String description;

    private LocalDateTime date;

    private List<CommentDto> commentDtos = new ArrayList<>();

    private int softwareId;

    private int userId;

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<CommentDto> getCommentDtos() {
        return commentDtos;
    }

    public void setCommentDtos(List<CommentDto> commentDtos) {
        this.commentDtos = commentDtos;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public QuestionDto() {
    }

    public int getSoftwareId() {
        return softwareId;
    }

    public void setSoftwareId(int softwareId) {
        this.softwareId = softwareId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public QuestionDto(int questionId, String description, LocalDateTime date, List<CommentDto> commentDtos,
            int softwareId, int userId) {
        this.questionId = questionId;
        this.description = description;
        this.date = date;
        this.commentDtos = commentDtos;
        this.softwareId = softwareId;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "QuestionDto [questionId=" + questionId + ", description=" + description + ", date=" + date
                + ", commentDtos=" + commentDtos + ", softwareId=" + softwareId + ", userId=" + userId + "]";
    }

}
