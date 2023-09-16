package com.websiteReview.Service;

import com.websiteReview.Dtos.ReviewDto;
import com.websiteReview.Helper.ReviewRequest;
import com.websiteReview.Helper.ReviewResponse;

public interface ReviewService {

        /**
         * Creates a new review for the specified software and user.
         *
         * @param username      The username of the user creating the review.
         * @param reviewRequest The review details.
         * @param softwareId    The ID of the software being reviewed.
         * @return The created ReviewDto.
         */
        public ReviewDto create(String username, ReviewRequest reviewRequest, int softwareId);

        /**
         * Retrieves a review by its ID.
         *
         * @param reviewId The ID of the review to retrieve.
         * @return The ReviewDto with the specified ID.
         */
        public ReviewDto viewById(int reviewId);

        /**
         * Retrieves reviews by a specific user with pagination.
         *
         * @param username   The username of the user whose reviews are to be retrieved.
         * @param pageNumber The page number for pagination.
         * @param pageSize   The number of reviews per page.
         * @return A ReviewResponse containing reviews by the specified user.
         */
        public ReviewResponse viewByUser(String username, int pageNumber, int pageSize);

        /**
         * Deletes a review by its ID.
         *
         * @param reviewId The ID of the review to delete.
         */
        public void delete(int reviewId);

        /**
         * Retrieves all reviews with pagination.
         *
         * @param pageNumber The page number for pagination.
         * @param pageSize   The number of reviews per page.
         * @return A ReviewResponse containing all reviews.
         */
        public ReviewResponse viewAll(int pageNumber, int pageSize);

        /**
         * Retrieves reviews for a specific software with pagination.
         *
         * @param softwareId The ID of the software for which to retrieve reviews.
         * @param pageNumber The page number for pagination.
         * @param pageSize   The number of reviews per page.
         * @return A ReviewResponse containing reviews for the specified software.
         */
        public ReviewResponse viewBySoftware(int softwareId, int pageNumber, int pageSize);

        /**
         * Filters reviews by organization size with pagination.
         *
         * @param minSize    The minimum organization size.
         * @param maxSize    The maximum organization size.
         * @param pageNumber The page number for pagination.
         * @param pageSize   The number of reviews per page.
         * @return A ReviewResponse containing filtered reviews by organization size.
         */
        public ReviewResponse filterReviewsByOrganizationSize(int minSize, int maxSize, int pageNumber, int pageSize);

        /**
         * Retrieves reviews by rating with pagination.
         *
         * @param rating     The rating value.
         * @param pageNumber The page number for pagination.
         * @param pageSize   The number of reviews per page.
         * @return A ReviewResponse containing reviews with the specified rating.
         */
        public ReviewResponse viewByRating(int rating, int pageNumber, int pageSize);

        /**
         * Retrieves reviews by purpose with pagination.
         *
         * @param purpose    The purpose of the reviews.
         * @param pageNumber The page number for pagination.
         * @param pageSize   The number of reviews per page.
         * @return A ReviewResponse containing reviews with the specified purpose.
         */
        public ReviewResponse viewByPurpose(String purpose, int pageNumber, int pageSize);

        /**
         * Retrieves reviews by user role with pagination.
         *
         * @param userRole   The user role of the reviewer.
         * @param pageNumber The page number for pagination.
         * @param pageSize   The number of reviews per page.
         * @return A ReviewResponse containing reviews by users with the specified role.
         */
        public ReviewResponse viewByUserRole(String userRole, int pageNumber, int pageSize);
}
