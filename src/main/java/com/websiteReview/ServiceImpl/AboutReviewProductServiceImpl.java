package com.websiteReview.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websiteReview.Dtos.AboutReviewProductDto;
import com.websiteReview.Exception.ResourceNotFoundException;
import com.websiteReview.Helper.DtoToModel;
import com.websiteReview.Helper.ModelToDto;
import com.websiteReview.Model.AboutReviewProduct;
import com.websiteReview.Model.Review;
import com.websiteReview.Respository.AboutReviewProductRepository;
import com.websiteReview.Respository.ReviewRepository;
import com.websiteReview.Service.AboutReviewProductService;

@Service
public class AboutReviewProductServiceImpl implements AboutReviewProductService {

    @Autowired
    private AboutReviewProductRepository aboutReviewProductRepository;;

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public AboutReviewProductDto create(int reviewId, AboutReviewProductDto aboutReviewProductDto) {
        AboutReviewProduct aboutReviewProduct = DtoToModel.aboutReviewProduct(aboutReviewProductDto);

        aboutReviewProduct.setEaseOfUseRating(aboutReviewProduct.getEaseOfUseRating() / 2);
        aboutReviewProduct.setMeetsRequirementRating(aboutReviewProduct.getMeetsRequirementRating() / 2);
        aboutReviewProduct.setNotionDirectionRating(aboutReviewProduct.getNotionDirectionRating());
        aboutReviewProduct.setQualitySupportRating(aboutReviewProduct.getQualitySupportRating() / 2);

        aboutReviewProduct = this.aboutReviewProductRepository.save(aboutReviewProduct);

        return ModelToDto.aboutReviewProductDto(aboutReviewProduct);
    }

    @Override
    public AboutReviewProductDto viewByReview(int reviewId) {
        Review review = this.reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("The expected review is not found"));

        AboutReviewProduct aboutReviewProduct = this.aboutReviewProductRepository.findByReview(review);
        return ModelToDto.aboutReviewProductDto(aboutReviewProduct);
    }

    @Override
    public AboutReviewProductDto viewById(int reviewProductId) {
        AboutReviewProduct aboutReviewProduct = this.aboutReviewProductRepository.findById(reviewProductId).orElseThrow(
                () -> new ResourceNotFoundException("The expected review product has not been found..."));
        return ModelToDto.aboutReviewProductDto(aboutReviewProduct);
    }

}
