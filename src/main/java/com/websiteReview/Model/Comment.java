package com.websiteReview.Model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int commentId;

    private String description;

    private Date date;

    @OneToOne
    private User user;

    @ManyToOne
    private Question question;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Comment() {
    }

    public Comment(int commentId, String description, Date date, User user, Question question) {
        this.commentId = commentId;
        this.description = description;
        this.date = date;
        this.user = user;
        this.question = question;
    }

    @Override
    public String toString() {
        return "Comment [commentId=" + commentId + ", description=" + description + ", date=" + date + ", user=" + user
                + ", question=" + question + "]";
    }

}
