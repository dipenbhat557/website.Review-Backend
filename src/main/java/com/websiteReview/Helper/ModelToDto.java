package com.websiteReview.Helper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.websiteReview.Dtos.AboutReviewProductDto;
import com.websiteReview.Dtos.AboutReviewUserDto;
import com.websiteReview.Dtos.CategoryDto;
import com.websiteReview.Dtos.CommentDto;
import com.websiteReview.Dtos.CompanySizeDto;
import com.websiteReview.Dtos.PricingDto;
import com.websiteReview.Dtos.QuestionDto;
import com.websiteReview.Dtos.RefreshTokenDto;
import com.websiteReview.Dtos.ReviewDto;
import com.websiteReview.Dtos.SoftwareDto;
import com.websiteReview.Dtos.SubCategoryDto;
import com.websiteReview.Dtos.UserDto;
import com.websiteReview.Model.AboutReviewProduct;
import com.websiteReview.Model.AboutReviewUser;
import com.websiteReview.Model.Category;
import com.websiteReview.Model.Comment;
import com.websiteReview.Model.CompanySize;
import com.websiteReview.Model.Pricing;
import com.websiteReview.Model.Question;
import com.websiteReview.Model.RefreshToken;
import com.websiteReview.Model.Review;
import com.websiteReview.Model.Software;
import com.websiteReview.Model.SubCategory;
import com.websiteReview.Model.User;

@Component
public class ModelToDto {

    @Autowired
    private ModelMapper modelMapper;

    public AboutReviewProductDto aboutReviewProductDto(AboutReviewProduct aboutReviewProduct) {
        AboutReviewProductDto aboutReviewProductDto = modelMapper.map(aboutReviewProduct, AboutReviewProductDto.class);
        aboutReviewProductDto.setReviewId(aboutReviewProduct.getReview().getReviewId());
        return aboutReviewProductDto;
    }

    public AboutReviewUserDto aboutReviewUserDto(AboutReviewUser aboutReviewUser) {
        AboutReviewUserDto aboutReviewUserDto = modelMapper.map(aboutReviewUser, AboutReviewUserDto.class);
        aboutReviewUserDto.setReviewId(aboutReviewUser.getReview().getReviewId());
        return aboutReviewUserDto;
    }

    public CategoryDto categoryDto(Category category) {
        CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);

        List<SubCategory> subCategories = category.getSubCategories();
        List<SubCategoryDto> subCategoryDtos = subCategories.stream()
                .map(subCategory -> modelMapper.map(subCategory, SubCategoryDto.class))
                .collect(Collectors.toList());
        categoryDto.setSubCategoryDtos(subCategoryDtos);

        return categoryDto;
    }

    public CommentDto commentDto(Comment comment) {
        CommentDto commentDto = modelMapper.map(comment, CommentDto.class);

        Question question = comment.getQuestion();
        User user = comment.getUser();
        commentDto.setQuestionId(question.getQuestionId());
        commentDto.setUserId(user.getUserId());

        return commentDto;
    }

    public CompanySizeDto companySizeDto(CompanySize companySize) {
        CompanySizeDto companySizeDto = modelMapper.map(companySize, CompanySizeDto.class);

        return companySizeDto;
    }

    public PricingDto pricingDto(Pricing pricing) {
        PricingDto pricingDto = modelMapper.map(pricing, PricingDto.class);

        Software software = pricing.getSoftware();
        pricingDto.setSoftwareId(software.getSoftwareId());

        return pricingDto;
    }

    public QuestionDto questionDto(Question question) {
        QuestionDto questionDto = modelMapper.map(question, QuestionDto.class);

        List<Comment> comments = question.getComments();
        Software software = question.getSoftware();
        User user = question.getUser();

        questionDto.setCommentDtos(comments.stream().map(comment -> modelMapper.map(comment, CommentDto.class))
                .collect(Collectors.toList()));
        questionDto.setSoftwareId(software.getSoftwareId());
        questionDto.setUserId(user.getUserId());

        return questionDto;
    }

    public RefreshTokenDto refreshTokenDto(RefreshToken refreshToken) {
        RefreshTokenDto refreshTokenDto = modelMapper.map(refreshToken, RefreshTokenDto.class);

        User user = refreshToken.getUser();
        refreshTokenDto.setUserId(user.getUserId());

        return refreshTokenDto;
    }

    public ReviewDto reviewDto(Review review) {
        ReviewDto reviewDto = modelMapper.map(review, ReviewDto.class);

        Software software = review.getSoftware();
        AboutReviewProduct aboutReviewProduct = review.getAboutReviewProduct();
        AboutReviewUser aboutReviewUser = review.getAboutReviewUser();
        User user = review.getUser();

        reviewDto.setSoftwareId(software.getSoftwareId());
        if (aboutReviewProduct != null) {
            reviewDto.setAboutReviewProductDto(modelMapper.map(aboutReviewProduct, AboutReviewProductDto.class));
        }

        if (aboutReviewUser != null) {
            reviewDto.setAboutReviewUserDto(modelMapper.map(aboutReviewUser, AboutReviewUserDto.class));
        }

        reviewDto.setUserId(user.getUserId());

        return reviewDto;
    }

    public SoftwareDto software(Software software) {
        SoftwareDto softwareDto = modelMapper.map(software, SoftwareDto.class);

        List<Review> reviews = software.getReviews();
        SubCategory subCategory = software.getSubCategory();
        List<Pricing> pricings = software.getPricings();
        CompanySize companySize = software.getCompanySize();
        List<Question> questions = software.getQuestions();

        softwareDto.setReviewIds(
                reviews.stream().map(review -> review.getReviewId()).collect(Collectors.toList()));
        softwareDto.setSubCategoryDto(modelMapper.map(subCategory, SubCategoryDto.class));
        softwareDto.setPricingDtos(pricings.stream().map(pricing -> modelMapper.map(pricing, PricingDto.class))
                .collect(Collectors.toList()));
        softwareDto.setCompanySizeDto(modelMapper.map(companySize, CompanySizeDto.class));
        softwareDto.setQuestionIds(questions.stream().map(question -> question.getQuestionId())
                .collect(Collectors.toList()));

        return softwareDto;
    }

    public SubCategoryDto subCategoryDto(SubCategory subCategory) {
        SubCategoryDto subCategoryDto = modelMapper.map(subCategory, SubCategoryDto.class);

        return subCategoryDto;
    }

    public UserDto userDto(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        userDto.setRefreshTokenDto(this.modelMapper.map(user.getRefreshToken(), RefreshTokenDto.class));
        userDto.setReviewIds(
                user.getReviews().stream().map(review -> review.getReviewId()).collect(Collectors.toList()));
        return userDto;
    }

}
