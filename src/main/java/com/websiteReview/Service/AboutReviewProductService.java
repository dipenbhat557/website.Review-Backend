package com.websiteReview.Service;

import com.websiteReview.Dtos.AboutReviewProductDto;
import com.websiteReview.Helper.AboutReviewProductRequest;

public interface AboutReviewProductService {

    public AboutReviewProductDto create(int reviewId, AboutReviewProductRequest aboutReviewProductRequest);

    public AboutReviewProductDto viewByReview(int ReviewId);

    public AboutReviewProductDto viewById(int reviewProductId);

    public void delete(int aboutReviewProductId);

}
