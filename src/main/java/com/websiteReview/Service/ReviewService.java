package com.websiteReview.Service;

import com.websiteReview.Dtos.ReviewDto;
import com.websiteReview.Helper.ReviewRequest;
import com.websiteReview.Helper.ReviewResponse;

public interface ReviewService {

        public ReviewDto create(String username, ReviewRequest reviewRequest, int softwareId);

        public ReviewDto viewById(int reviewId);

        public ReviewResponse viewByUser(String username, int pageNumber, int pageSize);

        public void delete(int reviewId);

        public ReviewResponse viewAll(int pageNumber, int pageSize);

        public ReviewResponse viewBySoftware(int softwareId, int pageNumber, int pageSize);

        public ReviewResponse filterReviewsByOrganizationSize(int minSize, int maxSize, int pageNumber, int pageSize);

        public ReviewResponse viewByRating(int rating, int pageNumber, int pageSize);

        public ReviewResponse viewByPurpose(String purpose, int pageNumber, int pageSize);

        public ReviewResponse viewByUserRole(String userRole, int pageNumber, int pageSize);

}
