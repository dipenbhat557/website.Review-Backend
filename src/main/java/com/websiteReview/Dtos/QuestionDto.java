package com.websiteReview.Dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuestionDto {

    private int questionId;

    private Date date;

    private List<CommentDto> commentDtos = new ArrayList<>();

    private SoftwareDto softwareDto;

    private UserDto userDto;

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<CommentDto> getCommentDtos() {
        return commentDtos;
    }

    public void setCommentDtos(List<CommentDto> commentDtos) {
        this.commentDtos = commentDtos;
    }

    public SoftwareDto getSoftwareDto() {
        return softwareDto;
    }

    public void setSoftwareDto(SoftwareDto softwareDto) {
        this.softwareDto = softwareDto;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public QuestionDto() {
    }

    public QuestionDto(int questionId, Date date, List<CommentDto> commentDtos, SoftwareDto softwareDto,
            UserDto userDto) {
        this.questionId = questionId;
        this.date = date;
        this.commentDtos = commentDtos;
        this.softwareDto = softwareDto;
        this.userDto = userDto;
    }

    @Override
    public String toString() {
        return "QuestionDto [questionId=" + questionId + ", date=" + date
                + ", softwareDto=" + softwareDto + ", userDto=" + userDto + "]";
    }

}
