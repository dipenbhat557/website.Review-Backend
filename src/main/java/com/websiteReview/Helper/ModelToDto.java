package com.websiteReview.Helper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.websiteReview.Dtos.AboutReviewProductDto;
import com.websiteReview.Dtos.AboutReviewUserDto;
import com.websiteReview.Dtos.CategoryDto;
import com.websiteReview.Dtos.CommentDto;
import com.websiteReview.Dtos.CompanySizeDto;
import com.websiteReview.Dtos.FeaturesDto;
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
import com.websiteReview.Model.Features;
import com.websiteReview.Model.Pricing;
import com.websiteReview.Model.Question;
import com.websiteReview.Model.RefreshToken;
import com.websiteReview.Model.Review;
import com.websiteReview.Model.Software;
import com.websiteReview.Model.SubCategory;
import com.websiteReview.Model.User;

public class ModelToDto {

    @Autowired
    private static ModelMapper modelMapper;

    public static AboutReviewProductDto aboutReviewProductDto(AboutReviewProduct aboutReviewProduct) {
        AboutReviewProductDto aboutReviewProductDto = modelMapper.map(aboutReviewProduct, AboutReviewProductDto.class);
        aboutReviewProductDto.setReviewDto(modelMapper.map(aboutReviewProduct.getReview(), ReviewDto.class));
        return aboutReviewProductDto;
    }

    public static AboutReviewUserDto aboutReviewUserDto(AboutReviewUser aboutReviewUser) {
        AboutReviewUserDto aboutReviewUserDto = modelMapper.map(aboutReviewUser, AboutReviewUserDto.class);
        aboutReviewUserDto.setReviewDto(modelMapper.map(aboutReviewUser.getReview(), ReviewDto.class));
        return aboutReviewUserDto;
    }

    public static CategoryDto categoryDto(Category category) {
        CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);

        List<SubCategory> subCategories = category.getSubCategories();
        List<SubCategoryDto> subCategoryDtos = subCategories.stream()
                .map(subCategory -> modelMapper.map(subCategory, SubCategoryDto.class))
                .collect(Collectors.toList());
        categoryDto.setSubCategoryDtos(subCategoryDtos);

        return categoryDto;
    }

    public static CommentDto commentDto(Comment comment) {
        CommentDto commentDto = modelMapper.map(comment, CommentDto.class);

        Question question = comment.getQuestion();
        User user = comment.getUser();
        commentDto.setQuestionDto(modelMapper.map(question, QuestionDto.class));
        commentDto.setUserDto(modelMapper.map(user, UserDto.class));

        return commentDto;
    }

    public static CompanySizeDto companySizeDto(CompanySize companySize) {
        CompanySizeDto companySizeDto = modelMapper.map(companySize, CompanySizeDto.class);

        List<Software> softwares = companySize.getSoftwares();
        List<SoftwareDto> softwarerDtos = softwares.stream()
                .map(software -> modelMapper.map(software, SoftwareDto.class)).collect(Collectors.toList());
        companySizeDto.setSoftwareDtos(softwarerDtos);

        return companySizeDto;
    }

    public static FeaturesDto featuresDto(Features features) {
        FeaturesDto featuresDto = modelMapper.map(features, FeaturesDto.class);
        return featuresDto;
    }

    public static PricingDto pricingDto(Pricing pricing) {
        PricingDto pricingDto = modelMapper.map(pricing, PricingDto.class);

        Software software = pricing.getSoftware();
        SoftwareDto softwareDto = modelMapper.map(software, SoftwareDto.class);
        pricingDto.setSoftwareDto(softwareDto);

        return pricingDto;
    }

    public static QuestionDto questionDto(Question question) {
        QuestionDto questionDto = modelMapper.map(question, QuestionDto.class);

        List<Comment> comments = question.getComments();
        Software software = question.getSoftware();
        User user = question.getUser();

        questionDto.setCommentDtos(comments.stream().map(comment -> modelMapper.map(comment, CommentDto.class))
                .collect(Collectors.toList()));
        questionDto.setSoftwareDto(modelMapper.map(software, SoftwareDto.class));
        questionDto.setUserDto(modelMapper.map(user, UserDto.class));

        return questionDto;
    }

    public static RefreshTokenDto refreshTokenDto(RefreshToken refreshToken) {
        RefreshTokenDto refreshTokenDto = modelMapper.map(refreshToken, RefreshTokenDto.class);

        User user = refreshToken.getUser();
        refreshTokenDto.setUserDto(modelMapper.map(user, UserDto.class));

        return refreshTokenDto;
    }

    public static ReviewDto reviewDto(Review review) {
        ReviewDto reviewDto = modelMapper.map(review, ReviewDto.class);

        Software software = review.getSoftware();
        AboutReviewProduct aboutReviewProduct = review.getAboutReviewProduct();
        AboutReviewUser aboutReviewUser = review.getAboutReviewUser();
        User user = review.getUser();

        reviewDto.setSoftwareDto(modelMapper.map(software, SoftwareDto.class));
        reviewDto.setAboutReviewProductDto(modelMapper.map(aboutReviewProduct, AboutReviewProductDto.class));
        reviewDto.setAboutReviewUserDto(modelMapper.map(aboutReviewUser, AboutReviewUserDto.class));
        reviewDto.setUserDto(modelMapper.map(user, UserDto.class));

        return reviewDto;
    }

    public static SoftwareDto software(Software software) {
        SoftwareDto softwareDto = modelMapper.map(software, SoftwareDto.class);

        List<Review> reviews = software.getReviews();
        SubCategory subCategory = software.getSubCategory();
        List<Pricing> pricings = software.getPricings();
        CompanySize companySize = software.getCompanySize();

        softwareDto.setReviewDtos(
                reviews.stream().map(review -> modelMapper.map(review, ReviewDto.class)).collect(Collectors.toList()));
        softwareDto.setSubCategoryDto(modelMapper.map(subCategory, SubCategoryDto.class));
        softwareDto.setPricingDtos(pricings.stream().map(pricing -> modelMapper.map(pricing, PricingDto.class))
                .collect(Collectors.toList()));
        softwareDto.setCompanySizeDto(modelMapper.map(companySize, CompanySizeDto.class));

        return softwareDto;
    }

    public static SubCategoryDto subCategoryDto(SubCategory subCategory) {
        SubCategoryDto subCategoryDto = modelMapper.map(subCategory, SubCategoryDto.class);

        List<Category> categories = subCategory.getCategories();
        List<Software> softwares = subCategory.getSoftwares();

        subCategoryDto.setCategoryDtos(categories.stream().map(category -> modelMapper.map(category, CategoryDto.class))
                .collect(Collectors.toList()));
        subCategoryDto.setSoftwareDtos(softwares.stream().map(software -> modelMapper.map(software, SoftwareDto.class))
                .collect(Collectors.toList()));

        return subCategoryDto;
    }

    public static UserDto userDto(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }

}
