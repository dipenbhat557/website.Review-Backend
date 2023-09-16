package com.websiteReview.Service;

import com.websiteReview.Dtos.AboutReviewUserDto;
import com.websiteReview.Helper.AboutReviewUserRequest;

public interface AboutReviewUserService {

    /**
     * Create an about review user associated with a specific review.
     *
     * @param reviewId               The ID of the review to which the about review
     *                               user belongs.
     * @param aboutReviewUserRequest The request containing information to create
     *                               the about review user.
     * @return The created AboutReviewUserDto.
     */
    public AboutReviewUserDto create(int reviewId, AboutReviewUserRequest aboutReviewUserRequest);

    /**
     * View the about review user associated with a specific review.
     *
     * @param reviewId The ID of the review for which to view the about review user.
     * @return The AboutReviewUserDto representing the about review user.
     */
    public AboutReviewUserDto viewByReview(int reviewId);

    /**
     * View an about review user by its unique identifier.
     *
     * @param reviewUserId The unique ID of the about review user to view.
     * @return The AboutReviewUserDto representing the about review user.
     */
    public AboutReviewUserDto viewById(int reviewUserId);

    /**
     * Update an existing about review user with new information.
     *
     * @param reviewUserId       The unique ID of the about review user to update.
     * @param aboutReviewUserDto The AboutReviewUserDto containing updated
     *                           information.
     * @return The updated AboutReviewUserDto.
     */
    public AboutReviewUserDto update(int reviewUserId, AboutReviewUserDto aboutReviewUserDto);

    /**
     * Delete an about review user by its unique identifier.
     *
     * @param aboutReviewUserId The unique ID of the about review user to delete.
     */
    public void delete(int aboutReviewUserId);
}
