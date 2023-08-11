package com.websiteReview.Service;

import com.websiteReview.Dtos.QuestionDto;
import com.websiteReview.Helper.QuestionRequest;
import com.websiteReview.Helper.QuestionResponse;

public interface QuestionService {

        public QuestionDto create(QuestionRequest questionRequest, String username, int softwareId);

        public QuestionDto viewById(int questionId);

        public QuestionResponse viewBySoftware(int softwareId, int pageNumber, int pageSize);

        public QuestionResponse viewByUser(String username, int pageNumber, int pageSize);

        public void delete(int questionId);

        public QuestionDto update(QuestionDto questionDto, int questionId, String username);

}
