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

    private SoftwareDto softwareDto;

    private UserDto userDto;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public QuestionDto() {
    }

    public QuestionDto(int questionId, String description, LocalDateTime date, List<CommentDto> commentDtos,
            SoftwareDto softwareDto, UserDto userDto) {
        this.questionId = questionId;
        this.description = description;
        this.date = date;
        this.commentDtos = commentDtos;
        this.softwareDto = softwareDto;
        this.userDto = userDto;
    }

    @Override
    public String toString() {
        return "QuestionDto [questionId=" + questionId + ", description=" + description + ", date=" + date
                + ", commentDtos=" + commentDtos + ", softwareDto=" + softwareDto + ", userDto=" + userDto + "]";
    }

}
