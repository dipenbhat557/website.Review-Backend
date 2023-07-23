package com.websiteReview.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.websiteReview.Exception.ResourceNotFoundException;
import com.websiteReview.Helper.AboutReviewProductDto;
import com.websiteReview.Helper.ReviewDto;
import com.websiteReview.Model.AboutReviewProduct;
import com.websiteReview.Model.Review;
import com.websiteReview.Respository.AboutReviewProductRepository;

import jakarta.persistence.Entity;

@Entity
public class AboutReviewProductService {

    @Autowired
    private AboutReviewProductRepository aboutReviewProductRepository;;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ReviewService reviewService;

    public AboutReviewProductDto create(int reviewId, AboutReviewProductDto aboutReviewProductDto) {
        AboutReviewProduct aboutReviewProduct = this.modelMapper.map(aboutReviewProductDto, AboutReviewProduct.class);

        ReviewDto reviewDto = this.reviewService.viewById(reviewId);
        aboutReviewProduct.setReview(this.modelMapper.map(reviewDto,Review.class));

        return this.modelMapper.map(this.aboutReviewProductRepository.save(aboutReviewProduct),
                AboutReviewProductDto.class);
    }

    public AboutReviewProductDto viewByReview(int ReviewId) {
        ReviewDto reviewDto = this.reviewService.viewById(ReviewId);
        return this.modelMapper.map(this.aboutReviewProductRepository.findByReview(this.modelMapper.map(reviewDto,Review.class)),
                AboutReviewProductDto.class);
    }

    public AboutReviewProductDto viewById(int reviewProductId) {
        return this.modelMapper.map(
                this.aboutReviewProductRepository.findById(reviewProductId).orElseThrow
                        (() -> new ResourceNotFoundException("The expected review product has not been found..."))
                ,AboutReviewProductDto.class);
    }
}
