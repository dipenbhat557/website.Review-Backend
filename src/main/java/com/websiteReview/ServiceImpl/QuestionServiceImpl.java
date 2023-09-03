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

import com.websiteReview.Dtos.QuestionDto;
import com.websiteReview.Exception.ResourceNotFoundException;
import com.websiteReview.Helper.ModelToDto;
import com.websiteReview.Helper.QuestionRequest;
import com.websiteReview.Helper.QuestionResponse;
import com.websiteReview.Model.Question;
import com.websiteReview.Model.Software;
import com.websiteReview.Model.User;
import com.websiteReview.Respository.QuestionRepository;
import com.websiteReview.Respository.SoftwareRepository;
import com.websiteReview.Respository.UserRepository;
import com.websiteReview.Service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

        @Autowired
        private QuestionRepository questionRepository;

        @Autowired
        private SoftwareRepository softwareRepository;

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private ModelToDto ModelToDto;

        @Override
        public QuestionDto create(QuestionRequest questionRequest, String username, int softwareId) {
                Question question = new Question();
                question.setDescription(questionRequest.getDescription());
                Software software = this.softwareRepository.findById(softwareId)
                                .orElseThrow(() -> new ResourceNotFoundException("The expected software is not found"));
                User user = this.userRepository.findByEmail(username)
                                .orElseThrow(() -> new ResourceNotFoundException("The expected software is not found"));

                question.setSoftware(software);
                question.setUser(user);
                question.setDate(LocalDateTime.now());

                Question savedQuestion = this.questionRepository.save(question);

                return ModelToDto.questionDto(savedQuestion);
        }

        @Override
        public QuestionDto viewById(int questionId) {
                Question question = this.questionRepository.findById(questionId)
                                .orElseThrow(() -> new ResourceNotFoundException("The expected question is not found"));
                return ModelToDto.questionDto(question);
        }

        @Override
        public QuestionResponse viewBySoftware(int softwareId, int pageNumber, int pageSize) {

                Software software = this.softwareRepository.findById(softwareId)
                                .orElseThrow(() -> new ResourceNotFoundException("The expected software is not found"));

                Pageable pageable = PageRequest.of(pageNumber, pageSize);
                Page<Question> page = this.questionRepository.findBySoftware(software, pageable);
                List<Question> pageQuestion = page.getContent();

                List<QuestionDto> pageQuestionDtos = pageQuestion.stream()
                                .map(question -> ModelToDto.questionDto(question))
                                .collect(Collectors.toList());

                QuestionResponse response = new QuestionResponse();
                response.setContent(pageQuestionDtos);
                response.setPageNumber(page.getNumber());
                response.setPageSize(page.getSize());
                response.setTotalPages(page.getTotalPages());
                response.setLastPage(page.isLast());
                return response;
        }

        @Override
        public QuestionResponse viewByUser(String username, int pageNumber, int pageSize) {

                User user = this.userRepository.findByEmail(username)
                                .orElseThrow(() -> new ResourceNotFoundException("The expected user is not found"));

                Pageable pageable = PageRequest.of(pageNumber, pageSize);
                Page<Question> page = this.questionRepository.findByUser(user, pageable);
                List<Question> pageQuestions = page.getContent();
                List<QuestionDto> pageQuestionDtos = pageQuestions.stream()
                                .map(question -> ModelToDto.questionDto(question))
                                .collect(Collectors.toList());

                QuestionResponse response = new QuestionResponse();
                response.setContent(pageQuestionDtos);
                response.setPageNumber(page.getNumber());
                response.setPageSize(page.getSize());
                response.setTotalPages(page.getTotalPages());
                response.setLastPage(page.isLast());
                return response;
        }

        @Override
        public void delete(int questionId) {
                Question question = this.questionRepository.findById(questionId)
                                .orElseThrow(() -> new ResourceNotFoundException("The expected question is not found"));
                this.questionRepository.delete(question);
        }

        @Override
        public QuestionDto update(QuestionRequest questionRequest, int questionId, String username) {
                Question oldQuestion = this.questionRepository.findById(questionId)
                                .orElseThrow(() -> new ResourceNotFoundException("The expected question is not found"));

                User newUser = this.userRepository.findByEmail(username)
                                .orElseThrow(() -> new ResourceNotFoundException("The expected user is not found"));
                if (oldQuestion.getUser().equals(newUser)) {

                        oldQuestion.setDescription(questionRequest.getDescription());
                        oldQuestion = this.questionRepository.save(oldQuestion);
                } else {
                        throw new ResourceNotFoundException("You are not allowed to perform this operation");
                }

                return ModelToDto.questionDto(oldQuestion);
        }

}
