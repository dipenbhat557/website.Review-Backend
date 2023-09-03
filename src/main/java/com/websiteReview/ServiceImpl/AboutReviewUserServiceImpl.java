package com.websiteReview.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websiteReview.Dtos.AboutReviewUserDto;
import com.websiteReview.Exception.ResourceNotFoundException;
import com.websiteReview.Helper.AboutReviewUserRequest;
import com.websiteReview.Helper.ModelToDto;
import com.websiteReview.Model.AboutReviewProduct;
import com.websiteReview.Model.AboutReviewUser;
import com.websiteReview.Model.Review;
import com.websiteReview.Respository.AboutReviewUserRepository;
import com.websiteReview.Respository.ReviewRepository;
import com.websiteReview.Service.AboutReviewUserService;

@Service
public class AboutReviewUserServiceImpl implements AboutReviewUserService {

        @Autowired
        private AboutReviewUserRepository aboutReviewUserRepository;

        @Autowired
        private ReviewRepository reviewRepository;

        @Autowired
        private ModelToDto ModelToDto;

        @Override
        public AboutReviewUserDto create(int reviewId, AboutReviewUserRequest aboutReviewUserRequest) {
                AboutReviewUser aboutReviewUser = new AboutReviewUser();

                aboutReviewUser.setOrganizationSize(aboutReviewUserRequest.getOrganizationSize());
                aboutReviewUser.setCurrentUser(aboutReviewUserRequest.isCurrentUser());
                aboutReviewUser.setSwitchFromAnother(aboutReviewUserRequest.isSwitchFromAnother());
                aboutReviewUser.setPreviousVendor(aboutReviewUserRequest.getPreviousVendor());
                aboutReviewUser.setReasonOfSwitch(aboutReviewUserRequest.getReasonOfSwitch());

                Review review = this.reviewRepository.findById(reviewId)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "The expected review has not been found"));
                aboutReviewUser.setReview(review);

                return ModelToDto.aboutReviewUserDto(this.aboutReviewUserRepository.save(aboutReviewUser));
        }

        @Override
        public AboutReviewUserDto viewByReview(int reviewId) {
                Review review = this.reviewRepository.findById(reviewId)
                                .orElseThrow(() -> new ResourceNotFoundException("The expected review is not found"));
                return ModelToDto.aboutReviewUserDto(
                                this.aboutReviewUserRepository.findByReview(review));
        }

        @Override
        public AboutReviewUserDto viewById(int reviewUserId) {
                return ModelToDto.aboutReviewUserDto(
                                this.aboutReviewUserRepository.findById(reviewUserId).orElseThrow(
                                                () -> new ResourceNotFoundException(
                                                                "The expected review user has not been found...")));
        }

        @Override
        public AboutReviewUserDto update(int reviewUserId, AboutReviewUserDto aboutReviewUserDto) {

                AboutReviewUser oldUser = this.aboutReviewUserRepository.findById(reviewUserId)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "The expected review user has not been found..."));

                oldUser.setOrganizationSize(aboutReviewUserDto.getOrganizationSize());
                oldUser.setCurrentUser(aboutReviewUserDto.isCurrentUser());
                oldUser.setScreenshotName(aboutReviewUserDto.getScreenshotName());
                oldUser.setSwitchFromAnother(aboutReviewUserDto.isSwitchFromAnother());
                oldUser.setPreviousVendor(aboutReviewUserDto.getPreviousVendor());
                oldUser.setReasonOfSwitch(aboutReviewUserDto.getReasonOfSwitch());

                return ModelToDto.aboutReviewUserDto(this.aboutReviewUserRepository.save(oldUser));
        }

        @Override
        public void delete(int aboutReviewUserId) {
                AboutReviewUser aboutReviewUser = this.aboutReviewUserRepository.findById(aboutReviewUserId)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "The expected about review user is not found"));
                this.aboutReviewUserRepository.delete(aboutReviewUser);
        }

}
