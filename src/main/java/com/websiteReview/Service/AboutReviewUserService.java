package com.websiteReview.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websiteReview.Exception.ResourceNotFoundException;
import com.websiteReview.Helper.AboutReviewUserDto;
import com.websiteReview.Helper.ReviewDto;
import com.websiteReview.Model.AboutReviewUser;
import com.websiteReview.Model.Review;
import com.websiteReview.Respository.AboutReviewUserRepository;

@Service
public class AboutReviewUserService {

    @Autowired
    private AboutReviewUserRepository aboutReviewUserRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ReviewService reviewService;

    public AboutReviewUserDto create(int reviewId, AboutReviewUserDto aboutReviewUserDto) {
        AboutReviewUser aboutReviewUser = this.modelMapper.map(aboutReviewUserDto, AboutReviewUser.class);

        ReviewDto reviewDto = this.reviewService.viewById(reviewId);
        aboutReviewUser.setReview(this.modelMapper.map(reviewDto,Review.class));

        return this.modelMapper.map(this.aboutReviewUserRepository.save(aboutReviewUser), AboutReviewUserDto.class);
    }

    public AboutReviewUserDto viewByReview(int ReviewId){
        ReviewDto reviewDto = this.reviewService.viewById(ReviewId);
        return this.modelMapper.map(this.aboutReviewUserRepository.findByReview(this.modelMapper.map(reviewDto,Review.class)), AboutReviewUserDto.class);
    }

    public AboutReviewUserDto viewById(int reviewUserId){
        return this.modelMapper.map(this.aboutReviewUserRepository.findById(reviewUserId).orElseThrow(() -> new ResourceNotFoundException("The expected review user has not been found...")),AboutReviewUserDto.class);
    }

    public AboutReviewUserDto update(int reviewUserId, AboutReviewUserDto aboutReviewUserDto){

        AboutReviewUser oldUser = this.aboutReviewUserRepository.findById(reviewUserId).orElseThrow(() -> new ResourceNotFoundException("The expected review user has not been found..."));

        oldUser.setOrganizationSize(aboutReviewUserDto.getOrganizationSize());
        oldUser.setCurrentUser(aboutReviewUserDto.isCurrentUser());
        oldUser.setScreenshotName(aboutReviewUserDto.getScreenshotName());
        oldUser.setSwitchFromAnother(aboutReviewUserDto.isSwitchFromAnother());
        oldUser.setPreviousVendor(aboutReviewUserDto.getPreviousVendor());
        oldUser.setReasonOfSwitch(aboutReviewUserDto.getReasonOfSwitch());

        return this.modelMapper.map(this.aboutReviewUserRepository.save(oldUser),AboutReviewUserDto.class);
    }

}
