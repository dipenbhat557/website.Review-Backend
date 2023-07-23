package com.websiteReview.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websiteReview.Dtos.AboutReviewProductDto;
import com.websiteReview.Dtos.ReviewDto;
import com.websiteReview.Exception.ResourceNotFoundException;
import com.websiteReview.Model.AboutReviewProduct;
import com.websiteReview.Model.Review;
import com.websiteReview.Respository.AboutReviewProductRepository;

@Service
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
        aboutReviewProduct.setReview(this.modelMapper.map(reviewDto, Review.class));

        aboutReviewProduct.setEaseOfUseRating(aboutReviewProduct.getEaseOfUseRating() / 2);
        aboutReviewProduct.setMeetsRequirementRating(aboutReviewProduct.getMeetsRequirementRating() / 2);
        aboutReviewProduct.setNotionDirectionRating(aboutReviewProduct.getNotionDirectionRating());
        aboutReviewProduct.setQualitySupportRating(aboutReviewProduct.getQualitySupportRating() / 2);

        return this.modelMapper.map(this.aboutReviewProductRepository.save(aboutReviewProduct),
                AboutReviewProductDto.class);
    }

    public AboutReviewProductDto viewByReview(int ReviewId) {
        ReviewDto reviewDto = this.reviewService.viewById(ReviewId);
        return this.modelMapper.map(
                this.aboutReviewProductRepository.findByReview(this.modelMapper.map(reviewDto, Review.class)),
                AboutReviewProductDto.class);
    }

    public AboutReviewProductDto viewById(int reviewProductId) {
        return this.modelMapper.map(
                this.aboutReviewProductRepository.findById(reviewProductId).orElseThrow(
                        () -> new ResourceNotFoundException("The expected review product has not been found...")),
                AboutReviewProductDto.class);
    }

}
