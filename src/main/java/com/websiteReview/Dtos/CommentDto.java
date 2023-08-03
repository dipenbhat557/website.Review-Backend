package com.websiteReview.Dtos;

import java.util.Date;

public class CommentDto {

    private int commentId;

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

    public CommentDto() {
    }

    public CommentDto(int commentId, Date date, UserDto userDto, QuestionDto questionDto) {
        this.commentId = commentId;
        this.date = date;
        this.userDto = userDto;
        this.questionDto = questionDto;
    }

    @Override
    public String toString() {
        return "CommentDto [commentId=" + commentId + ", date=" + date + ", userDto=" + userDto + ", questionDto="
                + questionDto + "]";
    }

}
