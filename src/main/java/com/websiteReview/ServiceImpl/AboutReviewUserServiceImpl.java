package com.websiteReview.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websiteReview.Dtos.AboutReviewUserDto;
import com.websiteReview.Exception.ResourceNotFoundException;
import com.websiteReview.Helper.AboutReviewUserRequest;
import com.websiteReview.Helper.ModelToDto;
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
                // Create a new AboutReviewUser instance
                AboutReviewUser aboutReviewUser = new AboutReviewUser();

                // Set properties of aboutReviewUser from the request
                aboutReviewUser.setOrganizationSize(aboutReviewUserRequest.getOrganizationSize());
                aboutReviewUser.setCurrentUser(aboutReviewUserRequest.isCurrentUser());
                aboutReviewUser.setSwitchFromAnother(aboutReviewUserRequest.isSwitchFromAnother());
                aboutReviewUser.setPreviousVendor(aboutReviewUserRequest.getPreviousVendor());
                aboutReviewUser.setReasonOfSwitch(aboutReviewUserRequest.getReasonOfSwitch());

                // Retrieve the associated review using the provided reviewId
                Review review = this.reviewRepository.findById(reviewId)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "The expected review has not been found"));
                aboutReviewUser.setReview(review);

                // Save the newly created AboutReviewUser instance to the database
                return ModelToDto.aboutReviewUserDto(this.aboutReviewUserRepository.save(aboutReviewUser));
        }

        @Override
        public AboutReviewUserDto viewByReview(int reviewId) {
                // Retrieve the associated review using the provided reviewId
                Review review = this.reviewRepository.findById(reviewId)
                                .orElseThrow(() -> new ResourceNotFoundException("The expected review is not found"));

                // Find the AboutReviewUser associated with the review
                AboutReviewUser aboutReviewUser = this.aboutReviewUserRepository.findByReview(review);

                // Convert and return the AboutReviewUser as a DTO
                return ModelToDto.aboutReviewUserDto(aboutReviewUser);
        }

        @Override
        public AboutReviewUserDto viewById(int reviewUserId) {
                // Retrieve the AboutReviewUser by its ID
                return ModelToDto.aboutReviewUserDto(
                                this.aboutReviewUserRepository.findById(reviewUserId)
                                                .orElseThrow(() -> new ResourceNotFoundException(
                                                                "The expected review user has not been found...")));
        }

        @Override
        public AboutReviewUserDto update(int reviewUserId, AboutReviewUserDto aboutReviewUserDto) {
                // Retrieve the existing AboutReviewUser by its ID
                AboutReviewUser oldUser = this.aboutReviewUserRepository.findById(reviewUserId)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "The expected review user has not been found..."));

                // Update properties of the existing AboutReviewUser with the provided DTO
                oldUser.setOrganizationSize(aboutReviewUserDto.getOrganizationSize());
                oldUser.setCurrentUser(aboutReviewUserDto.isCurrentUser());
                oldUser.setScreenshotName(aboutReviewUserDto.getScreenshotName());
                oldUser.setSwitchFromAnother(aboutReviewUserDto.isSwitchFromAnother());
                oldUser.setPreviousVendor(aboutReviewUserDto.getPreviousVendor());
                oldUser.setReasonOfSwitch(aboutReviewUserDto.getReasonOfSwitch());

                // Save the updated AboutReviewUser to the database
                return ModelToDto.aboutReviewUserDto(this.aboutReviewUserRepository.save(oldUser));
        }

        @Override
        public void delete(int aboutReviewUserId) {
                // Retrieve the AboutReviewUser by its ID
                AboutReviewUser aboutReviewUser = this.aboutReviewUserRepository.findById(aboutReviewUserId)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "The expected about review user is not found"));

                // Delete the AboutReviewUser
                this.aboutReviewUserRepository.delete(aboutReviewUser);
        }
}
