package com.websiteReview.Service;

import com.websiteReview.Dtos.ReviewDto;
import com.websiteReview.Helper.ReviewResponse;

public interface ReviewService {

        public ReviewDto create(String username, ReviewDto reviewDto, int softwareId);

        public ReviewDto viewById(int reviewId);

        public ReviewResponse viewByUser(String username, int pageSize, int pageNumber);

        public void delete(int reviewId);

        public ReviewResponse filterReviewsByOrganizationSize(int minSize, int maxSize, int pageNumber, int pageSize);

        public ReviewResponse viewByRating(int rating, int pageNumber, int pageSize);

        public ReviewResponse viewByPurpose(String purpose, int pageNumber, int pageSize);

        public ReviewResponse viewByUserRole(String userRole, int pageNumber, int pageSize);

}
