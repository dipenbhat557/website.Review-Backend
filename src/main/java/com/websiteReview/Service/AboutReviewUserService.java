package com.websiteReview.Service;

import com.websiteReview.Dtos.AboutReviewUserDto;
import com.websiteReview.Helper.AboutReviewUserRequest;

public interface AboutReviewUserService {

    public AboutReviewUserDto create(int reviewId, AboutReviewUserRequest aboutReviewUserRequest);

    public AboutReviewUserDto viewByReview(int ReviewId);

    public AboutReviewUserDto viewById(int reviewUserId);

    public AboutReviewUserDto update(int reviewUserId, AboutReviewUserDto aboutReviewUserDto);

    public void delete(int aboutReviewUserId);

}
