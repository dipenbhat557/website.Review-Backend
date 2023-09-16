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
                // Find the question by its ID or throw an exception if not found
                Question question = this.questionRepository.findById(questionId)
                                .orElseThrow(() -> new ResourceNotFoundException("The expected question is not found"));

                // Find the user by their email or throw an exception if not found
                User user = this.userRepository.findByEmail(username)
                                .orElseThrow(() -> new ResourceNotFoundException("The expected user is not found"));

                // Create a new comment instance and set its properties
                Comment comment = new Comment();
                comment.setDescription(commentRequest.getDescription());
                comment.setDate(LocalDateTime.now());
                comment.setQuestion(question);
                comment.setUser(user);

                // Save the comment to the database
                comment = this.commentRepository.save(comment);

                // Convert and return the comment as a DTO
                CommentDto commentDto = ModelToDto.commentDto(comment);

                return commentDto;
        }

        @Override
        public CommentDto viewById(int commentId) {
                // Find the comment by its ID or throw an exception if not found
                Comment comment = this.commentRepository.findById(commentId)
                                .orElseThrow(() -> new ResourceNotFoundException("The expected comment is not found"));

                // Convert and return the comment as a DTO
                CommentDto commentDto = ModelToDto.commentDto(comment);

                return commentDto;
        }

        @Override
        public CommentResponse viewByUser(String username, int pageNumber, int pageSize) {
                // Find the user by their email or throw an exception if not found
                User user = this.userRepository.findByEmail(username)
                                .orElseThrow(() -> new ResourceNotFoundException("The expected user is not found"));

                // Create pageable for pagination
                Pageable pageable = PageRequest.of(pageNumber, pageSize);

                // Retrieve comments by user using pagination
                Page<Comment> page = this.commentRepository.findByUser(user, pageable);

                // Extract comments from the page
                List<Comment> pageComment = page.getContent();

                // Convert the list of comments to a list of DTOs
                List<CommentDto> pageCommentDto = pageComment.stream()
                                .map(comment -> ModelToDto.commentDto(comment))
                                .collect(Collectors.toList());

                // Create and populate a CommentResponse object for pagination and content
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
                // Find the question by its ID or throw an exception if not found
                Question question = this.questionRepository.findById(questionId)
                                .orElseThrow(() -> new ResourceNotFoundException("The expected question is not found"));

                // Create pageable for pagination
                Pageable pageable = PageRequest.of(pageNumber, pageSize);

                // Retrieve comments by question using pagination
                Page<Comment> page = this.commentRepository.findByQuestion(question, pageable);

                // Extract comments from the page
                List<Comment> pageComment = page.getContent();

                // Convert the list of comments to a list of DTOs
                List<CommentDto> pageCommentDto = pageComment.stream()
                                .map(comment -> ModelToDto.commentDto(comment))
                                .collect(Collectors.toList());

                // Create and populate a CommentResponse object for pagination and content
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
                // Find the user by their email or throw an exception if not found
                User user = this.userRepository.findByEmail(username)
                                .orElseThrow(() -> new ResourceNotFoundException("The expected user is not found"));

                // Find the comment by its ID or throw an exception if not found
                Comment comment = this.commentRepository.findById(commentId)
                                .orElseThrow(() -> new ResourceNotFoundException("The expected comment is not found"));

                // Get the user who originally posted the comment
                User oldUser = comment.getUser();

                // Check if the current user has the permission to update the comment
                if (user.equals(oldUser)) {
                        comment.setDescription(commentRequest.getDescription());
                        comment = this.commentRepository.save(comment);
                } else {
                        throw new ResourceNotFoundException("You do not have permission to perform this action");
                }

                // Convert and return the updated comment as a DTO
                CommentDto commentDto = ModelToDto.commentDto(comment);

                return commentDto;
        }

        @Override
        public void delete(int commentId) {
                // Find the comment by its ID or throw an exception if not found
                Comment comment = this.commentRepository.findById(commentId)
                                .orElseThrow(() -> new ResourceNotFoundException("The expected comment is not found"));

                // Delete the comment from the database
                this.commentRepository.delete(comment);
        }
}