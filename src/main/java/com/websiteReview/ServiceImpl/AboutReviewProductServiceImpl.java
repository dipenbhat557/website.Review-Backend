package com.websiteReview.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websiteReview.Dtos.AboutReviewProductDto;
import com.websiteReview.Exception.ResourceNotFoundException;
import com.websiteReview.Helper.AboutReviewProductRequest;
import com.websiteReview.Helper.DtoToModel;
import com.websiteReview.Helper.ModelToDto;
import com.websiteReview.Model.AboutReviewProduct;
import com.websiteReview.Model.Review;
import com.websiteReview.Model.Software;
import com.websiteReview.Respository.AboutReviewProductRepository;
import com.websiteReview.Respository.ReviewRepository;
import com.websiteReview.Respository.SoftwareRepository;
import com.websiteReview.Service.AboutReviewProductService;

@Service
public class AboutReviewProductServiceImpl implements AboutReviewProductService {

        @Autowired
        private AboutReviewProductRepository aboutReviewProductRepository;;

        @Autowired
        private ReviewRepository reviewRepository;

        @Autowired
        private DtoToModel DtoToModel;

        @Autowired
        private ModelToDto ModelToDto;

        @Autowired
        private SoftwareRepository softwareRepository;

        @Override
        public AboutReviewProductDto create(int reviewId, AboutReviewProductRequest aboutReviewProductRequest) {
                AboutReviewProduct aboutReviewProduct = new AboutReviewProduct();

                aboutReviewProduct.setTitle(aboutReviewProductRequest.getTitle());
                aboutReviewProduct.setPurposeOfUse(aboutReviewProductRequest.getPurposeOfUse());
                aboutReviewProduct.setEaseOfUseRating(aboutReviewProductRequest.getEaseOfUseRating() / 2);
                aboutReviewProduct.setMeetsRequirementRating(aboutReviewProductRequest.getMeetsRequirementRating() / 2);
                aboutReviewProduct.setNotionDirectionRating(aboutReviewProductRequest.getNotionDirectionRating() / 2);
                aboutReviewProduct.setQualitySupportRating(aboutReviewProductRequest.getQualitySupportRating() / 2);

                Review review = this.reviewRepository.findById(reviewId)
                                .orElseThrow(() -> new ResourceNotFoundException("The expected review is not found"));

                aboutReviewProduct.setReview(review);

                aboutReviewProduct = this.aboutReviewProductRepository.save(aboutReviewProduct);

                Software software = review.getSoftware();
                int noOfResponses = software.getNoOfResponses();

                double notionDirectionRating = software.getNotionDirectionRating();
                double easeOfUseRating = software.getEaseOfUseRating();
                double meetsRequirementRating = software.getMeetsRequirementRating();
                double qualitySupportRating = software.getQualitySupportRating();

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
                AboutReviewProduct aboutReviewProduct = this.aboutReviewProductRepository.findById(reviewProductId)
                                .orElseThrow(
                                                () -> new ResourceNotFoundException(
                                                                "The expected review product has not been found..."));
                return ModelToDto.aboutReviewProductDto(aboutReviewProduct);
        }

        @Override
        public void delete(int aboutReviewProductId) {
                System.out.println(aboutReviewProductId);
                AboutReviewProduct aboutReviewProduct = this.aboutReviewProductRepository.findById(aboutReviewProductId)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "The expected about review product is not found"));
                System.out.println(aboutReviewProduct);
                this.aboutReviewProductRepository.delete(aboutReviewProduct);
        }

}
