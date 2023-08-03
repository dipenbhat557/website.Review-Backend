package com.websiteReview.Service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websiteReview.Dtos.CommentDto;
import com.websiteReview.Respository.CommentRepository;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ModelMapper modelMapper;

    public CommentDto createComment(CommentDto commentDto, String username, int questionId) {
        return null;
    }

    public CommentDto viewById(int commentId) {
        return null;
    }

    public List<CommentDto> viewByUser(String username) {
        return null;
    }

    public List<CommentDto> viewByQuestion(int questionId) {
        return null;
    }

    public CommentDto updateComment(CommentDto commentDto) {
        return null;
    }

    public void deleteComment(int commentId) {

    }

}
