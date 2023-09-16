package com.websiteReview.ServiceImpl;

import java.time.LocalDateTime;
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
                // Create a new Question object and populate it with data from the request
                Question question = new Question();
                question.setDescription(questionRequest.getDescription());

                // Find the corresponding Software by its ID
                Software software = this.softwareRepository.findById(softwareId)
                                .orElseThrow(() -> new ResourceNotFoundException("The expected software is not found"));

                // Find the User by their username (email)
                User user = this.userRepository.findByEmail(username)
                                .orElseThrow(() -> new ResourceNotFoundException("The expected user is not found"));

                // Set the Software, User, and date for the question
                question.setSoftware(software);
                question.setUser(user);
                question.setDate(LocalDateTime.now());

                // Save the Question entity to the database
                Question savedQuestion = this.questionRepository.save(question);

                // Convert the saved Question entity to a Question DTO and return it
                return ModelToDto.questionDto(savedQuestion);
        }

        @Override
        public QuestionDto viewById(int questionId) {
                // Find the Question by its ID
                Question question = this.questionRepository.findById(questionId)
                                .orElseThrow(() -> new ResourceNotFoundException("The expected question is not found"));

                // Convert the Question entity to a Question DTO and return it
                return ModelToDto.questionDto(question);
        }

        @Override
        public QuestionResponse viewBySoftware(int softwareId, int pageNumber, int pageSize) {
                // Find the Software by its ID
                Software software = this.softwareRepository.findById(softwareId)
                                .orElseThrow(() -> new ResourceNotFoundException("The expected software is not found"));

                // Create a pageable request for pagination
                Pageable pageable = PageRequest.of(pageNumber, pageSize);

                // Retrieve a page of Questions associated with the Software
                Page<Question> page = this.questionRepository.findBySoftware(software, pageable);

                // Get the content of the page
                List<Question> pageQuestion = page.getContent();

                // Convert the list of Question entities to a list of Question DTOs
                List<QuestionDto> pageQuestionDtos = pageQuestion.stream()
                                .map(question -> ModelToDto.questionDto(question))
                                .collect(Collectors.toList());

                // Create a QuestionResponse object to hold the results and pagination info
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
                // Find the User by their username (email)
                User user = this.userRepository.findByEmail(username)
                                .orElseThrow(() -> new ResourceNotFoundException("The expected user is not found"));

                // Create a pageable request for pagination
                Pageable pageable = PageRequest.of(pageNumber, pageSize);

                // Retrieve a page of Questions created by the User
                Page<Question> page = this.questionRepository.findByUser(user, pageable);

                // Get the content of the page
                List<Question> pageQuestions = page.getContent();

                // Convert the list of Question entities to a list of Question DTOs
                List<QuestionDto> pageQuestionDtos = pageQuestions.stream()
                                .map(question -> ModelToDto.questionDto(question))
                                .collect(Collectors.toList());

                // Create a QuestionResponse object to hold the results and pagination info
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
                // Find the Question by its ID
                Question question = this.questionRepository.findById(questionId)
                                .orElseThrow(() -> new ResourceNotFoundException("The expected question is not found"));

                // Delete the Question from the database
                this.questionRepository.delete(question);
        }

        @Override
        public QuestionDto update(QuestionRequest questionRequest, int questionId, String username) {
                // Find the existing Question by its ID
                Question oldQuestion = this.questionRepository.findById(questionId)
                                .orElseThrow(() -> new ResourceNotFoundException("The expected question is not found"));

                // Find the User by their username (email)
                User newUser = this.userRepository.findByEmail(username)
                                .orElseThrow(() -> new ResourceNotFoundException("The expected user is not found"));

                // Check if the user requesting the update is the same as the original
                // question's user
                if (oldQuestion.getUser().equals(newUser)) {
                        // Update the description of the old Question with the new description from the
                        // request
                        oldQuestion.setDescription(questionRequest.getDescription());

                        // Save the updated Question to the database
                        oldQuestion = this.questionRepository.save(oldQuestion);
                } else {
                        // If the user is not allowed to perform this operation, throw an exception
                        throw new ResourceNotFoundException("You are not allowed to perform this operation");
                }

                // Convert the updated Question entity to a Question DTO and return it
                return ModelToDto.questionDto(oldQuestion);
        }
}
