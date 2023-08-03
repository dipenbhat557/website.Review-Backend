package com.websiteReview.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.websiteReview.Dtos.CommentDto;
import com.websiteReview.Dtos.QuestionDto;
import com.websiteReview.Service.CommentService;
import com.websiteReview.Service.QuestionService;

@RestController
@RequestMapping("/discussion")
public class DiscussionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    //question part
    @PostMapping("/question/create/{softwareId}")
    public ResponseEntity<QuestionDto> createQuestion(@RequestBody QuestionDto questionDto, Principal principal,
            @PathVariable int softwareId) {
        QuestionDto savedQuestionDto = this.questionService.createQuestion(questionDto, principal.getName(),
                softwareId);
        return new ResponseEntity<QuestionDto>(savedQuestionDto, HttpStatus.CREATED);
    }

    @GetMapping("/question/viewById/{questionId}")
    public ResponseEntity<QuestionDto> viewQuestionById(@PathVariable int questionId){
        return new ResponseEntity<QuestionDto>(this.questionService.viewById(questionId), HttpStatus.OK);
    }

    @GetMapping("/question/viewByUser")
    public ResponseEntity<List<QuestionDto>> viewQuestionsByUser(Principal principal){
        return new ResponseEntity<List<QuestionDto>>(this.questionService.viewByUser(principal.getName()), HttpStatus.OK);
    }

    @GetMapping("/question/viewBySoftware/{softwareId}")
    public ResponseEntity<List<QuestionDto>> viewQuestionsBySoftware(@PathVariable int softwareId){
        return new ResponseEntity<List<QuestionDto>>(this.questionService.viewBySoftware(softwareId), HttpStatus.OK);
    }

    @GetMapping("/question/delete/{questionId}")
    public ResponseEntity<String> deleteQuestion(@PathVariable int questionId){
        this.questionService.deleteQuestion(questionId);
        return new ResponseEntity<String>("Successfully deleted question...", HttpStatus.OK);
    }

    @PutMapping("/question/update")
    public ResponseEntity<QuestionDto> updateQuestion(@RequestBody QuestionDto questionDto){
        return new ResponseEntity<QuestionDto>(this.questionService.updateQuestion(questionDto), HttpStatus.OK);
    }


    //comments started
    @PostMapping("/comment/create/{questionId}")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, Principal principal, @PathVariable int questionId){
        return new ResponseEntity<CommentDto>(this.commentService.createComment(commentDto, principal.getName(), questionId), HttpStatus.CREATED);
    }

    @GetMapping("/comment/viewById/{commentId}")
    public ResponseEntity<CommentDto> viewCommentById(@PathVariable int commentId){
        return new ResponseEntity<CommentDto>(this.commentService.viewById(commentId), HttpStatus.OK);
    }

    @GetMapping("/comment/viewByUser")
    public ResponseEntity<List<CommentDto>> viewCommentsByUser(Principal principal){
        return new ResponseEntity<List<CommentDto>>(this.commentService.viewByUser(principal.getName()), HttpStatus.OK);
    }

    @GetMapping("/comment/viewByQuestion/{questionId}")
    public ResponseEntity<List<CommentDto>> viewCommentsByQuestion(@PathVariable int questionId){
        return new ResponseEntity<List<CommentDto>>(this.commentService.viewByQuestion(questionId), HttpStatus.OK);
    }

    @GetMapping("/comment/delete/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable int commentId){
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<String>("Successfully deleted...", HttpStatus.OK);
    }

    @PutMapping("/comment/update")
    public ResponseEntity<CommentDto> updateComment(@RequestBody CommentDto commentDto){
        return new ResponseEntity<CommentDto>(this.commentService.updateComment(commentDto), HttpStatus.OK);
    }

}
