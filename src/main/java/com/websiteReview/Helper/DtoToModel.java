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

@Component
public class DtoToModel {

    @Autowired
    private ModelMapper modelMapper;

    public AboutReviewProduct aboutReviewProduct(AboutReviewProductDto aboutReviewProductDto) {
        AboutReviewProduct aboutReviewProduct = modelMapper.map(aboutReviewProductDto, AboutReviewProduct.class);
        aboutReviewProduct.setReview(modelMapper.map(aboutReviewProductDto.getReviewDto(), Review.class));
        return aboutReviewProduct;
    }

    public AboutReviewUser aboutReviewUser(AboutReviewUserDto aboutReviewUserDto) {
        AboutReviewUser aboutReviewUser = modelMapper.map(aboutReviewUserDto, AboutReviewUser.class);
        aboutReviewUser.setReview(modelMapper.map(aboutReviewUserDto.getReviewDto(), Review.class));
        return aboutReviewUser;
    }

    public Category category(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);

        List<SubCategoryDto> subCategoryDtos = categoryDto.getSubCategoryDtos();
        List<SubCategory> subCategories = subCategoryDtos.stream()
                .map(subCategoryDto -> modelMapper.map(subCategoryDto, SubCategory.class))
                .collect(Collectors.toList());
        category.setSubCategories(subCategories);

        return category;
    }

    public Comment comment(CommentDto commentDto) {
        Comment comment = modelMapper.map(commentDto, Comment.class);

        QuestionDto questionDto = commentDto.getQuestionDto();
        UserDto userDto = commentDto.getUserDto();
        comment.setQuestion(modelMapper.map(questionDto, Question.class));
        comment.setUser(modelMapper.map(userDto, User.class));

        return comment;
    }

    public CompanySize companySize(CompanySizeDto companySizeDto) {
        CompanySize companySize = modelMapper.map(companySizeDto, CompanySize.class);

        List<SoftwareDto> softwareDtos = companySizeDto.getSoftwareDtos();
        List<Software> softwarers = softwareDtos.stream()
                .map(softwareDto -> modelMapper.map(softwareDto, Software.class)).collect(Collectors.toList());
        companySize.setSoftwares(softwarers);

        return companySize;
    }

    public Features features(FeaturesDto featuresDto) {
        Features features = modelMapper.map(featuresDto, Features.class);
        return features;
    }

    public Pricing pricing(PricingDto pricingDto) {
        Pricing pricing = modelMapper.map(pricingDto, Pricing.class);

        SoftwareDto softwareDto = pricingDto.getSoftwareDto();
        Software software = modelMapper.map(softwareDto, Software.class);
        pricing.setSoftware(software);

        return pricing;
    }

    public Question question(QuestionDto questionDto) {
        Question question = modelMapper.map(questionDto, Question.class);

        List<CommentDto> commentDtos = questionDto.getCommentDtos();
        SoftwareDto softwareDto = questionDto.getSoftwareDto();
        UserDto userDto = questionDto.getUserDto();

        question.setComments(commentDtos.stream().map(commentDto -> modelMapper.map(commentDto, Comment.class))
                .collect(Collectors.toList()));
        question.setSoftware(modelMapper.map(softwareDto, Software.class));
        question.setUser(modelMapper.map(userDto, User.class));

        return question;
    }

    public RefreshToken refreshToken(RefreshTokenDto refreshTokenDto) {
        RefreshToken refreshToken = modelMapper.map(refreshTokenDto, RefreshToken.class);

        UserDto userDto = refreshTokenDto.getUserDto();
        refreshToken.setUser(modelMapper.map(userDto, User.class));

        return refreshToken;
    }

    public Review review(ReviewDto reviewDto) {
        Review review = modelMapper.map(reviewDto, Review.class);

        SoftwareDto softwareDto = reviewDto.getSoftwareDto();
        AboutReviewProductDto aboutReviewProductDto = reviewDto.getAboutReviewProductDto();
        AboutReviewUserDto aboutReviewUserDto = reviewDto.getAboutReviewUserDto();
        UserDto userDto = reviewDto.getUserDto();

        review.setSoftware(modelMapper.map(softwareDto, Software.class));
        review.setAboutReviewProduct(modelMapper.map(aboutReviewProductDto, AboutReviewProduct.class));
        review.setAboutReviewUser(modelMapper.map(aboutReviewUserDto, AboutReviewUser.class));
        review.setUser(modelMapper.map(userDto, User.class));

        return review;
    }

    public Software software(SoftwareDto softwareDto) {
        Software software = modelMapper.map(softwareDto, Software.class);

        List<ReviewDto> reviewDtos = softwareDto.getReviewDtos();
        SubCategoryDto subCategoryDto = softwareDto.getSubCategoryDto();
        List<PricingDto> pricingDtos = softwareDto.getPricingDtos();
        CompanySizeDto companySizeDto = softwareDto.getCompanySizeDto();

        software.setReviews(
                reviewDtos.stream().map(reviewDto -> modelMapper.map(reviewDto, Review.class))
                        .collect(Collectors.toList()));
        software.setSubCategory(modelMapper.map(subCategoryDto, SubCategory.class));
        software.setPricings(pricingDtos.stream().map(pricingDto -> modelMapper.map(pricingDto, Pricing.class))
                .collect(Collectors.toList()));
        software.setCompanySize(modelMapper.map(companySizeDto, CompanySize.class));

        return software;
    }

    public SubCategory subCategory(SubCategoryDto subCategoryDto) {
        SubCategory subCategory = modelMapper.map(subCategoryDto, SubCategory.class);

        List<CategoryDto> categoryDtos = subCategoryDto.getCategoryDtos();
        List<SoftwareDto> softwareDtos = subCategoryDto.getSoftwareDtos();

        subCategory.setCategories(categoryDtos.stream().map(categoryDto -> modelMapper.map(categoryDto, Category.class))
                .collect(Collectors.toList()));
        subCategory.setSoftwares(softwareDtos.stream().map(softwareDto -> modelMapper.map(softwareDto, Software.class))
                .collect(Collectors.toList()));

        return subCategory;
    }

    public User user(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        return user;
    }

}
