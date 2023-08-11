package com.websiteReview.Service;

import com.websiteReview.Dtos.CommentDto;
import com.websiteReview.Helper.CommentRequest;
import com.websiteReview.Helper.CommentResponse;

public interface CommentService {

    public CommentDto create(CommentRequest commentRequest, String username, int questionId);

    public CommentDto viewById(int commentId);

    public CommentResponse viewByUser(String username, int pageNumber, int pageSize);

    public CommentResponse viewByQuestion(int questionId, int pageNumber, int pageSize);

    public CommentDto update(int commentId, CommentDto commentDto, String username);

    public void delete(int commentId);

}
