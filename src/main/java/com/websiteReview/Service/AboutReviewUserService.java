package com.websiteReview.Service;

import com.websiteReview.Dtos.AboutReviewUserDto;

public interface AboutReviewUserService {

    public AboutReviewUserDto create(int reviewId, AboutReviewUserDto aboutReviewUserDto);

    public AboutReviewUserDto viewByReview(int ReviewId);

    public AboutReviewUserDto viewById(int reviewUserId);

    public AboutReviewUserDto update(int reviewUserId, AboutReviewUserDto aboutReviewUserDto);

}
