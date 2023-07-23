package com.websiteReview.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.websiteReview.Dtos.ReviewDto;
import com.websiteReview.Dtos.UserDto;
import com.websiteReview.Exception.ResourceNotFoundException;
import com.websiteReview.Helper.ReviewResponse;
import com.websiteReview.Model.Review;
import com.websiteReview.Model.User;
import com.websiteReview.Respository.ReviewRepository;
import com.websiteReview.Respository.UserRepository;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    public ReviewDto create(String username, ReviewDto reviewDto) {
        User user = this.userRepository.findByEmail(username).orElseThrow(
                () -> new ResourceNotFoundException("The expected user is not found while setting to review"));
        Review review = this.modelMapper.map(reviewDto, Review.class);
        review.setUser(user);
        review.setRating(review.getRating() / 2);
        ReviewDto savedReviewDto = this.modelMapper.map(this.reviewRepository.save(review), ReviewDto.class);
        savedReviewDto.setUserDto(this.modelMapper.map(user, UserDto.class));
        return savedReviewDto;
    }

    public ReviewDto viewById(int reviewId) {
        Review review = this.reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("The requested review is not found"));
        ReviewDto reviewDto = this.modelMapper.map(review, ReviewDto.class);
        User user = review.getUser();
        reviewDto.setUserDto(this.modelMapper.map(user, UserDto.class));
        return reviewDto;
    }

    public List<ReviewDto> viewByUser(String username) {
        User user = this.userRepository.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "The expected user is not found while getting review by user"));

        List<Review> list = this.reviewRepository.findByUser(user);
        System.out.println("Inside");
        System.out.println(list);

        List<ReviewDto> reviewsDtos = list.stream()
                .map((Review review) -> this.modelMapper.map(review, ReviewDto.class))
                .collect(Collectors.toList());

        UserDto userDto = this.modelMapper.map(user, UserDto.class);

        // Update the userDto property for each ReviewDto object in the list
        List<ReviewDto> newReviewsDtos = reviewsDtos.stream()
                .map((ReviewDto reviewsDto) -> {
                    reviewsDto.setUserDto(userDto);
                    return reviewsDto;
                })
                .collect(Collectors.toList());

        return newReviewsDtos;
    }

    public void delete(int reviewId) {
        Review review = this.reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("The expected review is not found while deleting..."));
        this.reviewRepository.delete(review);
    }

    public ReviewResponse filterReviewsByOrganizationSize(int minSize, int maxSize, int pageNumber, int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Review> page = this.reviewRepository.findByOrganizationSizeRange(minSize, maxSize, pageable);
        List<Review> pageReview = page.getContent();

        List<ReviewDto> pageReviewDtos = pageReview.stream().map(review -> this.modelMapper.map(review,ReviewDto.class)).collect(Collectors.toList());

        ReviewResponse response = new ReviewResponse();
        response.setContent(pageReviewDtos);
        response.setPageNumber(page.getNumber());
        response.setPageSize(page.getSize());
        response.setTotalPages(page.getTotalPages());
        response.setLastPage(page.isLast());

        return response;
    }

    public ReviewResponse filterReviewsByRating(int rating, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Review> page = this.reviewRepository.findByRatingRange(rating, rating + 1, pageable);
        List<Review> pageReview = page.getContent();

        List<ReviewDto> pageReviewDtos = pageReview.stream().map(review -> this.modelMapper.map(review,ReviewDto.class)).collect(Collectors.toList());

        ReviewResponse response = new ReviewResponse();
        response.setContent(pageReviewDtos);
        response.setPageNumber(page.getNumber());
        response.setPageSize(page.getSize());
        response.setTotalPages(page.getTotalPages());
        response.setLastPage(page.isLast());

        return response;
    }

    public ReviewResponse filterReviewsByPurpose(String purpose, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Review> page = this.reviewRepository.findByPurposeOfUse(purpose, pageable);
        List<Review> pageReview = page.getContent();

        List<ReviewDto> pageReviewDtos = pageReview.stream().map(review -> this.modelMapper.map(review,ReviewDto.class)).collect(Collectors.toList());

        ReviewResponse response = new ReviewResponse();
        response.setContent(pageReviewDtos);
        response.setPageNumber(page.getNumber());
        response.setPageSize(page.getSize());
        response.setTotalPages(page.getTotalPages());
        response.setLastPage(page.isLast());

        return response;
    }

    public ReviewResponse filterReviewsByUserRole(String userRole, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Review> page = this.reviewRepository.findByUserRole(userRole, pageable);
        List<Review> pageReview = page.getContent();

        List<ReviewDto> pageReviewDtos = pageReview.stream().map(review -> this.modelMapper.map(review,ReviewDto.class)).collect(Collectors.toList());

        ReviewResponse response = new ReviewResponse();
        response.setContent(pageReviewDtos);
        response.setPageNumber(page.getNumber());
        response.setPageSize(page.getSize());
        response.setTotalPages(page.getTotalPages());
        response.setLastPage(page.isLast());

        return response;
    }

}
