package com.websiteReview.ServiceImpl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.websiteReview.Dtos.CommentDto;
import com.websiteReview.Exception.ResourceNotFoundException;
import com.websiteReview.Helper.CommentRequest;
import com.websiteReview.Helper.CommentResponse;
import com.websiteReview.Helper.ModelToDto;
import com.websiteReview.Model.Comment;
import com.websiteReview.Model.Question;
import com.websiteReview.Model.User;
import com.websiteReview.Respository.CommentRepository;
import com.websiteReview.Respository.QuestionRepository;
import com.websiteReview.Respository.UserRepository;
import com.websiteReview.Service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

        @Autowired
        private CommentRepository commentRepository;

        @Autowired
        private QuestionRepository questionRepository;

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private ModelToDto ModelToDto;

        @Override
        public CommentDto create(CommentRequest commentRequest, String username, int questionId) {
                Question question = this.questionRepository.findById(questionId)
                                .orElseThrow(() -> new ResourceNotFoundException("The expected comment is not found"));

                User user = this.userRepository.findByEmail(username)
                                .orElseThrow(() -> new ResourceNotFoundException("The expected user is not found"));

                Comment comment = new Comment();
                comment.setDescription(commentRequest.getDescription());
                comment.setDate(LocalDateTime.now());
                comment.setQuestion(question);
                comment.setUser(user);

                comment = this.commentRepository.save(comment);

                CommentDto commentDto = ModelToDto.commentDto(comment);

                return commentDto;
        }

        @Override
        public CommentDto viewById(int commentId) {
                Comment comment = this.commentRepository.findById(commentId)
                                .orElseThrow(() -> new ResourceNotFoundException("The expected comment is not found"));

                CommentDto commentDto = ModelToDto.commentDto(comment);

                return commentDto;
        }

        @Override
        public CommentResponse viewByUser(String username, int pageNumber, int pageSize) {

                User user = this.userRepository.findByEmail(username)
                                .orElseThrow(() -> new ResourceNotFoundException("The expected user is not found"));

                Pageable pageable = PageRequest.of(pageNumber, pageSize);
                Page<Comment> page = this.commentRepository.findByUser(user, pageable);
                List<Comment> pageComment = page.getContent();

                List<CommentDto> pageCommentDto = pageComment.stream().map(comment -> ModelToDto.commentDto(comment))
                                .collect(Collectors.toList());

                CommentResponse response = new CommentResponse();
                response.setContent(pageCommentDto);
                response.setPageNumber(page.getNumber());
                response.setLastPage(page.isLast());
                response.setPageSize(page.getSize());
                response.setTotalPages(page.getTotalPages());
                return response;
        }

        @Override
        public CommentResponse viewByQuestion(int questionId, int pageNumber, int pageSize) {
                Question question = this.questionRepository.findById(questionId)
                                .orElseThrow(() -> new ResourceNotFoundException("The expected question is not found"));

                Pageable pageable = PageRequest.of(pageNumber, pageSize);
                Page<Comment> page = this.commentRepository.findByQuestion(question, pageable);
                List<Comment> pageComment = page.getContent();

                List<CommentDto> pageCommentDto = pageComment.stream()
                                .map(comment -> ModelToDto.commentDto(comment))
                                .collect(Collectors.toList());

                CommentResponse response = new CommentResponse();
                response.setContent(pageCommentDto);
                response.setPageNumber(page.getNumber());
                response.setLastPage(page.isLast());
                response.setPageSize(page.getSize());
                response.setTotalPages(page.getTotalPages());
                return response;
        }

        @Override
        public CommentDto update(int commentId, CommentRequest commentRequest, String username) {
                User user = this.userRepository.findByEmail(username)
                                .orElseThrow(() -> new ResourceNotFoundException("The expected user is not found"));

                Comment comment = this.commentRepository.findById(commentId)
                                .orElseThrow(() -> new ResourceNotFoundException("The expected comment is not found"));
                User oldUser = comment.getUser();

                if (user.equals(oldUser)) {
                        comment.setDescription(commentRequest.getDescription());
                        comment = this.commentRepository.save(comment);
                } else {
                        throw new ResourceNotFoundException(
                                        "You are not provided with the permission to perform this action");
                }

                CommentDto commentDto = ModelToDto.commentDto(comment);

                return commentDto;
        }

        @Override
        public void delete(int commentId) {

                Comment comment = this.commentRepository.findById(commentId)
                                .orElseThrow(() -> new ResourceNotFoundException("The expected comment is not found"));
                this.commentRepository.delete(comment);

        }

}
