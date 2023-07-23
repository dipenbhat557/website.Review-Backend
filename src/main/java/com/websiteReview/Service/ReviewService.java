package com.websiteReview.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websiteReview.Exception.ResourceNotFoundException;
import com.websiteReview.Helper.ReviewDto;
import com.websiteReview.Helper.UserDto;
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

    public ReviewDto create(String username, ReviewDto reviewDto){
        User user = this.userRepository.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("The expected user is not found while setting to review"));
        reviewDto.setUserDto(this.modelMapper.map(user, UserDto.class));
        return reviewDto;
    }

    public ReviewDto viewById(int reviewId){

        return this.modelMapper.map(this.reviewRepository.findById(reviewId).orElseThrow(() -> new ResourceNotFoundException("The requested review is not found")), ReviewDto.class);
    }

    public List<ReviewDto> viewByUser(String username){
        User user = this.userRepository.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException(("The expected user is not found while getting review by user")));
        List<Review> list = this.reviewRepository.findByUser(user);

        List<ReviewDto> reviews = list.stream().map((review) -> this.modelMapper.map(review,ReviewDto.class)).collect(Collectors.toList());

        return reviews;
    }
    
}
