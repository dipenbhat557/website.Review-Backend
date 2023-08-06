package com.websiteReview.Service;

import com.websiteReview.Dtos.AboutReviewProductDto;

public interface AboutReviewProductService {

    public AboutReviewProductDto create(int reviewId, AboutReviewProductDto aboutReviewProductDto);

    public AboutReviewProductDto viewByReview(int ReviewId);

    public AboutReviewProductDto viewById(int reviewProductId);

}
