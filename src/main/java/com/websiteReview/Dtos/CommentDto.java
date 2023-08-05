package com.websiteReview.Dtos;

import java.util.Date;

public class CommentDto {

    private int commentId;

    private String description;

    private Date date;

    private UserDto userDto;

    private QuestionDto questionDto;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public QuestionDto getQuestionDto() {
        return questionDto;
    }

    public void setQuestionDto(QuestionDto questionDto) {
        this.questionDto = questionDto;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CommentDto() {
    }

    public CommentDto(int commentId, String description, Date date, UserDto userDto, QuestionDto questionDto) {
        this.commentId = commentId;
        this.description = description;
        this.date = date;
        this.userDto = userDto;
        this.questionDto = questionDto;
    }

    @Override
    public String toString() {
        return "CommentDto [commentId=" + commentId + ", description=" + description + ", date=" + date + ", userDto="
                + userDto + ", questionDto=" + questionDto + "]";
    }

}
