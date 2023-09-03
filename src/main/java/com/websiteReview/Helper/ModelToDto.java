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
        AboutReviewProductDto aboutReviewProductDto = new AboutReviewProductDto();

        aboutReviewProductDto.setAboutProductId(aboutReviewProduct.getAboutProductId());
        aboutReviewProductDto.setTitle(aboutReviewProduct.getTitle());
        aboutReviewProductDto.setEaseOfUseRating(aboutReviewProduct.getEaseOfUseRating());
        aboutReviewProductDto.setMeetsRequirementRating(aboutReviewProduct.getMeetsRequirementRating());
        aboutReviewProductDto.setNotionDirectionRating(aboutReviewProduct.getNotionDirectionRating());
        aboutReviewProductDto.setPurposeOfUse(aboutReviewProduct.getPurposeOfUse());
        aboutReviewProductDto.setQualitySupportRating(aboutReviewProduct.getQualitySupportRating());
        aboutReviewProductDto.setReviewId(aboutReviewProduct.getReview().getReviewId());
        return aboutReviewProductDto;
    }

    public AboutReviewUserDto aboutReviewUserDto(AboutReviewUser aboutReviewUser) {
        AboutReviewUserDto aboutReviewUserDto = new AboutReviewUserDto();

        aboutReviewUserDto.setReviewUserId(aboutReviewUser.getReviewUserId());
        aboutReviewUserDto.setOrganizationSize(aboutReviewUser.getOrganizationSize());
        aboutReviewUserDto.setCurrentUser(aboutReviewUser.isCurrentUser());
        aboutReviewUserDto.setSwitchFromAnother(aboutReviewUser.isSwitchFromAnother());
        aboutReviewUserDto.setScreenshotName(aboutReviewUser.getScreenshotName());
        aboutReviewUserDto.setPreviousVendor(aboutReviewUser.getPreviousVendor());
        aboutReviewUserDto.setReasonOfSwitch(aboutReviewUser.getReasonOfSwitch());
        aboutReviewUserDto.setReviewId(aboutReviewUser.getReview().getReviewId());
        return aboutReviewUserDto;
    }

    public CategoryDto categoryDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setCategoryId(category.getCategoryId());
        categoryDto.setTitle(category.getTitle());
        categoryDto.setPurposes(category.getPurposes());
        List<SubCategory> subCategories = category.getSubCategories();
        List<SubCategoryDto> subCategoryDtos = subCategories.stream()
                .map(subCategory -> modelMapper.map(subCategory, SubCategoryDto.class))
                .collect(Collectors.toList());
        categoryDto.setSubCategoryDtos(subCategoryDtos);

        return categoryDto;
    }

    public CommentDto commentDto(Comment comment) {
        CommentDto commentDto = new CommentDto();

        commentDto.setCommentId(comment.getCommentId());
        commentDto.setDescription(comment.getDescription());
        commentDto.setDate(comment.getDate());

        User user = comment.getUser();
        commentDto.setUserId(user.getUserId());

        Question question = comment.getQuestion();
        commentDto.setQuestionId(question.getQuestionId());

        return commentDto;
    }

    public CompanySizeDto companySizeDto(CompanySize companySize) {
        CompanySizeDto companySizeDto = new CompanySizeDto();

        companySizeDto.setSizeId(companySize.getSizeId());
        companySizeDto.setTitle(companySize.getTitle());
        return companySizeDto;
    }

    public PricingDto pricingDto(Pricing pricing) {
        PricingDto pricingDto = new PricingDto();

        pricingDto.setPricingId(pricing.getPricingId());
        pricingDto.setTitle(pricing.getTitle());
        pricingDto.setFeatures(pricing.getFeatures());
        pricingDto.setPrice(pricing.getPrice());
        Software software = pricing.getSoftware();
        pricingDto.setSoftwareId(software.getSoftwareId());

        return pricingDto;
    }

    public QuestionDto questionDto(Question question) {
        QuestionDto questionDto = new QuestionDto();

        questionDto.setQuestionId(question.getQuestionId());
        questionDto.setDescription(question.getDescription());
        questionDto.setDate(question.getDate());

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
        RefreshTokenDto refreshTokenDto = new RefreshTokenDto();

        refreshTokenDto.setTokenId(refreshToken.getTokenId());
        refreshTokenDto.setRefreshToken(refreshToken.getRefreshToken());
        refreshTokenDto.setUserId(refreshToken.getUser().getUserId());
        refreshTokenDto.setExpiry(refreshToken.getExpiry());

        return refreshTokenDto;
    }

    public ReviewDto reviewDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();

        reviewDto.setReviewId(review.getReviewId());
        reviewDto.setTitle(review.getTitle());
        reviewDto.setWhatYouLike(review.getWhatYouLike());
        reviewDto.setWhatYouDislike(review.getWhatYouDislike());
        reviewDto.setUserRole(review.getUserRole());
        reviewDto.setRating(review.getRating());

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
        SoftwareDto softwareDto = new SoftwareDto();

        softwareDto.setSoftwareId(software.getSoftwareId());
        softwareDto.setTitle(software.getTitle());
        softwareDto.setDescription(software.getDescription());
        softwareDto.setLocation(software.getLocation());
        softwareDto.setYearFounded(software.getYearFounded());
        softwareDto.setLanguage(software.getLanguage());
        softwareDto.setDifferenceFromOthers(software.getDifferenceFromOthers());
        softwareDto.setProfileImageName(software.getProfileImageName());
        softwareDto.setWebsiteLink(software.getWebsiteLink());
        softwareDto.setTwitterId(software.getTwitterId());
        softwareDto.setLinkedInId(software.getLinkedInId());
        softwareDto.setFeatures(software.getFeatures());
        softwareDto.setVideoName(software.getVideoName());
        softwareDto.setScreenshots(software.getScreenshots());
        softwareDto.setNoOfResponses(software.getNoOfResponses());
        softwareDto.setRating(software.getRating());
        softwareDto.setNotionDirectionRating(software.getNotionDirectionRating());
        softwareDto.setEaseOfUseRating(software.getEaseOfUseRating());
        softwareDto.setMeetsRequirementRating(software.getMeetsRequirementRating());
        softwareDto.setQualitySupportRating(software.getQualitySupportRating());

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
        SubCategoryDto subCategoryDto = new SubCategoryDto();

        subCategoryDto.setSubCategoryId(subCategory.getSubCategoryId());
        subCategoryDto.setTitle(subCategory.getTitle());

        return subCategoryDto;
    }

    public UserDto userDto(User user) {
        UserDto userDto = new UserDto();

        userDto.setUserId(user.getUserId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setImageUrl(user.getImageUrl());
        userDto.setEmailVerified(user.getEmailVerified());
        userDto.setPassword(user.getPassword());
        userDto.setProvider(user.getProvider());
        userDto.setProviderId(user.getProviderId());

        userDto.setRefreshTokenDto(this.refreshTokenDto(user.getRefreshToken()));
        userDto.setReviewIds(
                user.getReviews().stream().map(review -> review.getReviewId()).collect(Collectors.toList()));
        return userDto;
    }

}
