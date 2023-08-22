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
import com.websiteReview.Dtos.QuestionDto;
import com.websiteReview.Helper.AppConstants;
import com.websiteReview.Helper.CommentRequest;
import com.websiteReview.Helper.CommentResponse;
import com.websiteReview.Helper.QuestionRequest;
import com.websiteReview.Helper.QuestionResponse;
import com.websiteReview.ServiceImpl.CommentServiceImpl;
import com.websiteReview.ServiceImpl.QuestionServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/discussion")
public class DiscussionController {

    @Autowired
    private QuestionServiceImpl questionService;

    @Autowired
    private CommentServiceImpl commentService;

    // question part
    @PostMapping("/question/create/{softwareId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<QuestionDto> createQuestion(@RequestBody QuestionRequest questionRequest, Principal principal,
            @PathVariable int softwareId) {
        QuestionDto savedQuestionDto = this.questionService.create(questionRequest, principal.getName(),
                softwareId);
        return new ResponseEntity<QuestionDto>(savedQuestionDto, HttpStatus.CREATED);
    }

    @GetMapping("/question/viewById/{questionId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<QuestionDto> viewQuestionById(
            @PathVariable int questionId) {
        return new ResponseEntity<QuestionDto>(this.questionService.viewById(questionId), HttpStatus.OK);
    }

    @GetMapping("/question/viewByUser")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<QuestionResponse> viewQuestionsByUser(Principal principal,
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.pageNumberString, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.pageSizeString, required = false) int pageSize) {
        return new ResponseEntity<QuestionResponse>(
                this.questionService.viewByUser(principal.getName(), pageNumber, pageSize),
                HttpStatus.OK);
    }

    @GetMapping("/question/viewBySoftware/{softwareId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<QuestionResponse> viewQuestionsBySoftware(@PathVariable int softwareId,
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.pageNumberString, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.pageSizeString, required = false) int pageSize) {
        return new ResponseEntity<QuestionResponse>(
                this.questionService.viewBySoftware(softwareId, pageNumber, pageSize), HttpStatus.OK);
    }

    @DeleteMapping("/question/delete/{questionId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<String> deleteQuestion(@PathVariable int questionId) {
        this.questionService.delete(questionId);
        return new ResponseEntity<String>("Successfully deleted question...", HttpStatus.OK);
    }

    @PutMapping("/question/update/{questionId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<QuestionDto> updateQuestion(@RequestBody QuestionRequest questionRequest,
            @PathVariable int questionId, Principal principal) {
        return new ResponseEntity<QuestionDto>(
                this.questionService.update(questionRequest, questionId, principal.getName()),
                HttpStatus.OK);
    }

    // comments started
    @PostMapping("/comment/create/{questionId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentRequest commentRequest, Principal principal,
            @PathVariable int questionId) {
        return new ResponseEntity<CommentDto>(
                this.commentService.create(commentRequest, principal.getName(), questionId), HttpStatus.CREATED);
    }

    @GetMapping("/comment/viewById/{commentId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<CommentDto> viewCommentById(@PathVariable int commentId) {
        return new ResponseEntity<CommentDto>(this.commentService.viewById(commentId), HttpStatus.OK);
    }

    @GetMapping("/comment/viewByUser")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<CommentResponse> viewCommentsByUser(Principal principal,
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.pageNumberString, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.pageSizeString, required = false) int pageSize) {
        return new ResponseEntity<CommentResponse>(
                this.commentService.viewByUser(principal.getName(), pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("/comment/viewByQuestion/{questionId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<CommentResponse> viewCommentsByQuestion(@PathVariable int questionId,
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.pageNumberString, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.pageSizeString, required = false) int pageSize) {
        return new ResponseEntity<CommentResponse>(this.commentService.viewByQuestion(questionId, pageNumber, pageSize),
                HttpStatus.OK);
    }

    @DeleteMapping("/comment/delete/{commentId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<String> deleteComment(@PathVariable int commentId) {
        this.commentService.delete(commentId);
        return new ResponseEntity<String>("Successfully deleted...", HttpStatus.OK);
    }

    @PutMapping("/comment/update/{commentId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<CommentDto> updateComment(@RequestBody CommentRequest commentRequest,
            @PathVariable int commentId,
            Principal principal) {
        return new ResponseEntity<CommentDto>(
                this.commentService.update(commentId, commentRequest, principal.getName()), HttpStatus.OK);
    }

}
