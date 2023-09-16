package com.websiteReview.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.websiteReview.Dtos.CommentDto;
import com.websiteReview.Helper.AppConstants;
import com.websiteReview.Helper.CommentRequest;
import com.websiteReview.Helper.CommentResponse;
import com.websiteReview.ServiceImpl.CommentServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/discussion/comment")
public class DiscussionCommentController {

    @Autowired
    private CommentServiceImpl commentService;

    // Create a new comment for a discussion question
    @PostMapping("/{questionId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentRequest commentRequest, Principal principal,
            @PathVariable int questionId) {
        return new ResponseEntity<CommentDto>(
                this.commentService.create(commentRequest, principal.getName(), questionId), HttpStatus.CREATED);
    }

    // Get a comment by its ID
    @GetMapping("/{commentId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<CommentDto> viewCommentById(@PathVariable int commentId) {
        return new ResponseEntity<CommentDto>(this.commentService.viewById(commentId), HttpStatus.OK);
    }

    // Get comments by question ID with pagination
    @GetMapping("/question/{questionId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<CommentResponse> viewCommentsByQuestion(@PathVariable int questionId,
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.pageNumberString, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.pageSizeString, required = false) int pageSize) {
        return new ResponseEntity<CommentResponse>(this.commentService.viewByQuestion(questionId, pageNumber, pageSize),
                HttpStatus.OK);
    }

    // Delete a comment by its ID
    @DeleteMapping("/{commentId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<String> deleteComment(@PathVariable int commentId) {
        this.commentService.delete(commentId);
        return new ResponseEntity<String>("Successfully deleted...", HttpStatus.OK);
    }

    // Update a comment by its ID
    @PutMapping("/{commentId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<CommentDto> updateComment(@RequestBody CommentRequest commentRequest,
            @PathVariable int commentId,
            Principal principal) {
        return new ResponseEntity<CommentDto>(
                this.commentService.update(commentId, commentRequest, principal.getName()), HttpStatus.OK);
    }
}
