package com.websiteReview.Service;

import com.websiteReview.Dtos.CommentDto;
import com.websiteReview.Helper.CommentRequest;
import com.websiteReview.Helper.CommentResponse;

public interface CommentService {

    /**
     * Create a new comment based on the provided CommentRequest.
     *
     * @param commentRequest The request containing information to create the
     *                       comment.
     * @param username       The username of the user creating the comment.
     * @param questionId     The ID of the associated question for the comment.
     * @return The created CommentDto.
     */
    public CommentDto create(CommentRequest commentRequest, String username, int questionId);

    /**
     * View a comment by its unique identifier.
     *
     * @param commentId The unique ID of the comment to view.
     * @return The CommentDto representing the comment.
     */
    public CommentDto viewById(int commentId);

    /**
     * View comments made by a specific user with pagination.
     *
     * @param username   The username of the user to filter comments by.
     * @param pageNumber The page number for pagination.
     * @param pageSize   The number of comments per page.
     * @return A CommentResponse containing a list of comments made by the user.
     */
    public CommentResponse viewByUser(String username, int pageNumber, int pageSize);

    /**
     * View comments associated with a specific question with pagination.
     *
     * @param questionId The ID of the question to filter comments by.
     * @param pageNumber The page number for pagination.
     * @param pageSize   The number of comments per page.
     * @return A CommentResponse containing a list of comments associated with the
     *         question.
     */
    public CommentResponse viewByQuestion(int questionId, int pageNumber, int pageSize);

    /**
     * Update an existing comment based on the provided CommentRequest.
     *
     * @param commentId      The unique ID of the comment to update.
     * @param commentRequest The request containing updated comment information.
     * @param username       The username of the user updating the comment.
     * @return The updated CommentDto.
     */
    public CommentDto update(int commentId, CommentRequest commentRequest, String username);

    /**
     * Delete a comment by its unique identifier.
     *
     * @param commentId The unique ID of the comment to delete.
     */
    public void delete(int commentId);

}
