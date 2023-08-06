package com.websiteReview.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.websiteReview.Dtos.ReviewDto;
import com.websiteReview.Exception.ResourceNotFoundException;
import com.websiteReview.Helper.DtoToModel;
import com.websiteReview.Helper.ModelToDto;
import com.websiteReview.Helper.ReviewResponse;
import com.websiteReview.Model.AboutReviewProduct;
import com.websiteReview.Model.Review;
import com.websiteReview.Model.Software;
import com.websiteReview.Model.User;
import com.websiteReview.Respository.ReviewRepository;
import com.websiteReview.Respository.SoftwareRepository;
import com.websiteReview.Respository.UserRepository;
import com.websiteReview.Service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

        @Autowired
        private ReviewRepository reviewRepository;

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private SoftwareRepository softwareRepository;

        @Override
        public ReviewDto create(String username, ReviewDto reviewDto, int softwareId) {
                User user = this.userRepository.findByEmail(username).orElseThrow(
                                () -> new ResourceNotFoundException(
                                                "The expected user is not found while setting to review"));

                AboutReviewProduct aboutReviewProduct = DtoToModel
                                .aboutReviewProduct(reviewDto.getAboutReviewProductDto());

                Software software = this.softwareRepository.findById(softwareId)
                                .orElseThrow(() -> new ResourceNotFoundException("The expected software is not found"));

                int noOfResponses = software.getNoOfResponses();
                double rating = software.getRating();
                double notionDirectionRating = software.getNotionDirectionRating();
                double easeOfUseRating = software.getEaseOfUseRating();
                double meetsRequirementRating = software.getMeetsRequirementRating();
                double qualitySupportRating = software.getQualitySupportRating();

                software.setNoOfResponses(noOfResponses + 1);
                software.setRating((rating * noOfResponses + reviewDto.getRating()) / software.getNoOfResponses());
                software.setNotionDirectionRating(
                                (notionDirectionRating * noOfResponses + aboutReviewProduct.getNotionDirectionRating())
                                                / software.getNoOfResponses());
                software.setEaseOfUseRating((easeOfUseRating * noOfResponses + aboutReviewProduct.getEaseOfUseRating())
                                / software.getNoOfResponses());
                software.setMeetsRequirementRating(
                                (meetsRequirementRating * noOfResponses
                                                + aboutReviewProduct.getMeetsRequirementRating())
                                                / software.getNoOfResponses());
                software.setQualitySupportRating(
                                (qualitySupportRating * noOfResponses + aboutReviewProduct.getQualitySupportRating())
                                                / software.getNoOfResponses());
                this.softwareRepository.save(software);

                Review review = DtoToModel.review(reviewDto);
                review.setUser(user);
                review.setRating(review.getRating() / 2);
                review.setSoftware(software);

                ReviewDto savedReviewDto = ModelToDto.reviewDto(review);

                return savedReviewDto;
        }

        @Override
        public ReviewDto viewById(int reviewId) {
                Review review = this.reviewRepository.findById(reviewId)
                                .orElseThrow(() -> new ResourceNotFoundException("The requested review is not found"));
                ReviewDto reviewDto = ModelToDto.reviewDto(review);
                return reviewDto;
        }

        @Override
        public ReviewResponse viewByUser(String username, int pageSize, int pageNumber) {
                User user = this.userRepository.findByEmail(username)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "The expected user is not found while getting review by user"));

                Pageable pageable = PageRequest.of(pageNumber, pageSize);
                Page<Review> page = this.reviewRepository.findByUser(user, pageable);
                List<Review> reviews = page.getContent();

                List<ReviewDto> reviewsDtos = reviews.stream()
                                .map(review -> ModelToDto.reviewDto(review))
                                .collect(Collectors.toList());

                ReviewResponse response = new ReviewResponse();
                response.setContent(reviewsDtos);
                response.setPageNumber(page.getNumber());
                response.setPageSize(page.getSize());
                response.setTotalPages(page.getTotalPages());
                response.setLastPage(page.isLast());

                return response;
        }

        @Override
        public void delete(int reviewId) {
                Review review = this.reviewRepository.findById(reviewId)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "The expected review is not found while deleting..."));
                this.reviewRepository.delete(review);
        }

        @Override
        public ReviewResponse filterReviewsByOrganizationSize(int minSize, int maxSize, int pageNumber, int pageSize) {

                Pageable pageable = PageRequest.of(pageNumber, pageSize);
                Page<Review> page = this.reviewRepository.findByOrganizationSizeRange(minSize, maxSize, pageable);
                List<Review> pageReview = page.getContent();

                List<ReviewDto> pageReviewDtos = pageReview.stream()
                                .map(review -> ModelToDto.reviewDto(review))
                                .collect(Collectors.toList());

                ReviewResponse response = new ReviewResponse();
                response.setContent(pageReviewDtos);
                response.setPageNumber(page.getNumber());
                response.setPageSize(page.getSize());
                response.setTotalPages(page.getTotalPages());
                response.setLastPage(page.isLast());

                return response;
        }

        @Override
        public ReviewResponse viewByRating(int rating, int pageNumber, int pageSize) {
                Pageable pageable = PageRequest.of(pageNumber, pageSize);
                Page<Review> page = this.reviewRepository.findByRatingRange(rating, rating + 1, pageable);
                List<Review> pageReview = page.getContent();

                List<ReviewDto> pageReviewDtos = pageReview.stream()
                                .map(review -> ModelToDto.reviewDto(review))
                                .collect(Collectors.toList());

                ReviewResponse response = new ReviewResponse();
                response.setContent(pageReviewDtos);
                response.setPageNumber(page.getNumber());
                response.setPageSize(page.getSize());
                response.setTotalPages(page.getTotalPages());
                response.setLastPage(page.isLast());

                return response;
        }

        @Override
        public ReviewResponse viewByPurpose(String purpose, int pageNumber, int pageSize) {
                Pageable pageable = PageRequest.of(pageNumber, pageSize);
                Page<Review> page = this.reviewRepository.findByPurposeOfUse(purpose, pageable);
                List<Review> pageReview = page.getContent();

                List<ReviewDto> pageReviewDtos = pageReview.stream()
                                .map(review -> ModelToDto.reviewDto(review))
                                .collect(Collectors.toList());

                ReviewResponse response = new ReviewResponse();
                response.setContent(pageReviewDtos);
                response.setPageNumber(page.getNumber());
                response.setPageSize(page.getSize());
                response.setTotalPages(page.getTotalPages());
                response.setLastPage(page.isLast());

                return response;
        }

        @Override
        public ReviewResponse viewByUserRole(String userRole, int pageNumber, int pageSize) {
                Pageable pageable = PageRequest.of(pageNumber, pageSize);
                Page<Review> page = this.reviewRepository.findByUserRole(userRole, pageable);
                List<Review> pageReview = page.getContent();

                List<ReviewDto> pageReviewDtos = pageReview.stream()
                                .map(review -> ModelToDto.reviewDto(review))
                                .collect(Collectors.toList());

                ReviewResponse response = new ReviewResponse();
                response.setContent(pageReviewDtos);
                response.setPageNumber(page.getNumber());
                response.setPageSize(page.getSize());
                response.setTotalPages(page.getTotalPages());
                response.setLastPage(page.isLast());

                return response;
        }

}
