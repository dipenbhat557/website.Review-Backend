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

import com.websiteReview.Dtos.QuestionDto;
import com.websiteReview.Helper.AppConstants;
import com.websiteReview.Helper.QuestionRequest;
import com.websiteReview.Helper.QuestionResponse;
import com.websiteReview.ServiceImpl.QuestionServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/discussion/question")
public class DiscussionQuestionController {

    @Autowired
    private QuestionServiceImpl questionService;

    // Create a new discussion question for a software
    @PostMapping("/{softwareId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<QuestionDto> createQuestion(@RequestBody QuestionRequest questionRequest, Principal principal,
            @PathVariable int softwareId) {
        QuestionDto savedQuestionDto = this.questionService.create(questionRequest, principal.getName(),
                softwareId);
        return new ResponseEntity<QuestionDto>(savedQuestionDto, HttpStatus.CREATED);
    }

    // Get a discussion question by its ID
    @GetMapping("/{questionId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<QuestionDto> viewQuestionById(
            @PathVariable int questionId) {
        return new ResponseEntity<QuestionDto>(this.questionService.viewById(questionId), HttpStatus.OK);
    }

    // Get questions by the user who posted them with pagination
    @GetMapping("/user")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<QuestionResponse> viewQuestionsByUser(Principal principal,
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.pageNumberString, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.pageSizeString, required = false) int pageSize) {
        return new ResponseEntity<QuestionResponse>(
                this.questionService.viewByUser(principal.getName(), pageNumber, pageSize),
                HttpStatus.OK);
    }

    // Get questions associated with a software by software ID with pagination
    @GetMapping("/software/{softwareId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<QuestionResponse> viewQuestionsBySoftware(@PathVariable int softwareId,
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.pageNumberString, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.pageSizeString, required = false) int pageSize) {
        return new ResponseEntity<QuestionResponse>(
                this.questionService.viewBySoftware(softwareId, pageNumber, pageSize), HttpStatus.OK);
    }

    // Delete a discussion question by its ID
    @DeleteMapping("/{questionId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<String> deleteQuestion(@PathVariable int questionId) {
        this.questionService.delete(questionId);
        return new ResponseEntity<String>("Successfully deleted question...", HttpStatus.OK);
    }

    // Update a discussion question by its ID
    @PutMapping("/{questionId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<QuestionDto> updateQuestion(@RequestBody QuestionRequest questionRequest,
            @PathVariable int questionId, Principal principal) {
        return new ResponseEntity<QuestionDto>(
                this.questionService.update(questionRequest, questionId, principal.getName()),
                HttpStatus.OK);
    }
}
