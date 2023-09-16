package com.websiteReview.Service;

import com.websiteReview.Dtos.QuestionDto;
import com.websiteReview.Helper.QuestionRequest;
import com.websiteReview.Helper.QuestionResponse;

public interface QuestionService {

        /**
         * Creates a new question for a software.
         *
         * @param questionRequest The question information to be created.
         * @param username        The username of the user creating the question.
         * @param softwareId      The ID of the software for which the question is
         *                        created.
         * @return The created QuestionDto.
         */
        public QuestionDto create(QuestionRequest questionRequest, String username, int softwareId);

        /**
         * Retrieves a question by its ID.
         *
         * @param questionId The ID of the question to retrieve.
         * @return The QuestionDto with the specified ID.
         */
        public QuestionDto viewById(int questionId);

        /**
         * Retrieves a paginated list of questions associated with a software.
         *
         * @param softwareId The ID of the software for which to retrieve questions.
         * @param pageNumber The page number of the results.
         * @param pageSize   The number of questions per page.
         * @return A QuestionResponse containing a list of QuestionDto objects.
         */
        public QuestionResponse viewBySoftware(int softwareId, int pageNumber, int pageSize);

        /**
         * Retrieves a paginated list of questions posted by a user.
         *
         * @param username   The username of the user.
         * @param pageNumber The page number of the results.
         * @param pageSize   The number of questions per page.
         * @return A QuestionResponse containing a list of QuestionDto objects.
         */
        public QuestionResponse viewByUser(String username, int pageNumber, int pageSize);

        /**
         * Deletes a question by its ID.
         *
         * @param questionId The ID of the question to delete.
         */
        public void delete(int questionId);

        /**
         * Updates an existing question.
         *
         * @param questionRequest The updated question information.
         * @param questionId      The ID of the question to update.
         * @param username        The username of the user updating the question.
         * @return The updated QuestionDto.
         */
        public QuestionDto update(QuestionRequest questionRequest, int questionId, String username);
}
