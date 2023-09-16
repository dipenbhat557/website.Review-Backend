package com.websiteReview.Service;

import com.websiteReview.Dtos.AboutReviewProductDto;
import com.websiteReview.Helper.AboutReviewProductRequest;

public interface AboutReviewProductService {

    /**
     * Create an about review product associated with a specific review.
     *
     * @param reviewId                  The ID of the review to which the about
     *                                  review product belongs.
     * @param aboutReviewProductRequest The request containing information to create
     *                                  the about review product.
     * @return The created AboutReviewProductDto.
     */
    public AboutReviewProductDto create(int reviewId, AboutReviewProductRequest aboutReviewProductRequest);

    /**
     * View the about review product associated with a specific review.
     *
     * @param ReviewId The ID of the review for which to view the about review
     *                 product.
     * @return The AboutReviewProductDto representing the about review product.
     */
    public AboutReviewProductDto viewByReview(int ReviewId);

    /**
     * View an about review product by its unique identifier.
     *
     * @param reviewProductId The unique ID of the about review product to view.
     * @return The AboutReviewProductDto representing the about review product.
     */
    public AboutReviewProductDto viewById(int reviewProductId);

    /**
     * Delete an about review product by its unique identifier.
     *
     * @param aboutReviewProductId The unique ID of the about review product to
     *                             delete.
     */
    public void delete(int aboutReviewProductId);
}
