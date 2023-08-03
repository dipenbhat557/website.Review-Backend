package com.websiteReview.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websiteReview.Dtos.QuestionDto;
import com.websiteReview.Respository.QuestionRepository;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public QuestionDto createQuestion(QuestionDto questionDto, String username, int softwareId){
        
        return null;
    }

    public QuestionDto viewById(int questionId){
        return null;
    }

    public List<QuestionDto> viewBySoftware(int softwareId){
        return null;
    }

    public List<QuestionDto> viewByUser(String username){
        return null;
    }

    public void deleteQuestion(int questionId){

    }

    public QuestionDto updateQuestion(QuestionDto questionDto){
        return null;
    }
    
}
