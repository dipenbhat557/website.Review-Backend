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
import com.websiteReview.Helper.ReviewRequest;
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

        @Autowired
        private DtoToModel DtoToModel;

        @Autowired
        private ModelToDto ModelToDto;

        @Override
        public ReviewDto create(String username, ReviewRequest reviewRequest, int softwareId) {
                User user = this.userRepository.findByEmail(username).orElseThrow(
                                () -> new ResourceNotFoundException(
                                                "The expected user is not found while setting to review"));

                Software software = this.softwareRepository.findById(softwareId)
                                .orElseThrow(() -> new ResourceNotFoundException("The expected software is not found"));

                int noOfResponses = software.getNoOfResponses();
                double rating = software.getRating();

                software.setNoOfResponses(noOfResponses + 1);
                software.setRating((rating * noOfResponses + reviewRequest.getRating()) / software.getNoOfResponses());

                Review review = new Review();
                review.setTitle(reviewRequest.getTitle());
                review.setWhatYouLike(reviewRequest.getWhatYouLike());
                review.setWhatYouDislike(reviewRequest.getWhatYouDislike());
                review.setUserRole(reviewRequest.getUserRole());

                review.setUser(user);
                review.setRating(reviewRequest.getRating() / 2);
                review.setSoftware(software);

                // List<Review> reviews = software.getReviews();
                // reviews.add(review);

                this.softwareRepository.save(software);

                ReviewDto savedReviewDto = ModelToDto.reviewDto(this.reviewRepository.save(review));

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
        public ReviewResponse viewByUser(String username,int pageNumber, int pageSize ) {
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

                AboutReviewProduct aboutReviewProduct = review.getAboutReviewProduct();

                Software software = review.getSoftware();
                software.setRating(software.getRating() * software.getNoOfResponses() - review.getRating());
                software.setEaseOfUseRating(software.getEaseOfUseRating() * software.getNoOfResponses() - aboutReviewProduct.getEaseOfUseRating());
                software.setMeetsRequirementRating(software.getMeetsRequirementRating() * software.getNoOfResponses() - aboutReviewProduct.getMeetsRequirementRating());
                software.setNotionDirectionRating(software.getNotionDirectionRating() * software.getNoOfResponses() - aboutReviewProduct.getNotionDirectionRating());
                software.setQualitySupportRating(software.getQualitySupportRating() * software.getNoOfResponses() - aboutReviewProduct.getQualitySupportRating());
                software.setNoOfResponses(software.getNoOfResponses() - 1);
                this.softwareRepository.save(software);
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
                Page<Review> page = this.reviewRepository.findByPurposeOfUseIgnoreCase(purpose, pageable);
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

        @Override
        public ReviewResponse viewAll(int pageNumber, int pageSize) {

                Pageable pageable = PageRequest.of(pageNumber, pageSize);
                Page<Review> page = this.reviewRepository.findAll(pageable);
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
}
