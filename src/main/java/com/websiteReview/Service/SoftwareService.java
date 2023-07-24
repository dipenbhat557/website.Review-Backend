package com.websiteReview.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websiteReview.Dtos.AboutReviewUserDto;
import com.websiteReview.Dtos.ReviewDto;
import com.websiteReview.Dtos.SoftwareDto;
import com.websiteReview.Exception.ResourceNotFoundException;
import com.websiteReview.Model.AboutReviewUser;
import com.websiteReview.Model.Review;
import com.websiteReview.Model.Software;
import com.websiteReview.Respository.SoftwareRepository;

@Service
public class SoftwareService {

    @Autowired
    private SoftwareRepository softwareRepository;
    
    @Autowired
    private ModelMapper modelMapper;

    public SoftwareDto createSoftware(SoftwareDto softwareDto){
        Software software = this.modelMapper.map(softwareDto, Software.class);
        List<ReviewDto> reviewDtos = softwareDto.getReviewDtos();
        List<Review> reviews = reviewDtos.stream().map((reviewDto) -> this.modelMapper.map(reviewDto, Review.class)).collect(Collectors.toList());
        software.setReviews(reviews);

        software = this.softwareRepository.save(software);

        softwareDto.setSoftwareId(software.getSoftwareId());

        return softwareDto;
    }

    public SoftwareDto getById(int softwareId){
        Software software = this.softwareRepository.findById(softwareId).orElseThrow(() -> new ResourceNotFoundException("The expected software is not found"));
        return this.modelMapper.map(software,SoftwareDto.class);
    }

    public List<SoftwareDto> getAllSoftwares(){
        List<Software> softwares = this.softwareRepository.findAll();
        List<SoftwareDto> softwareDtos = softwares.stream().map(software -> this.modelMapper.map(software, SoftwareDto.class)).collect(Collectors.toList());
        return softwareDtos;
    }

    public void deleteSoftware(int softwareId){
        this.softwareRepository.delete(this.softwareRepository.findById(softwareId).orElseThrow(() -> new ResourceNotFoundException("The expected resource is not found while trying to delete it")));
    }

    public List<SoftwareDto> filterByRating(){
        return null;
    }

    public List<SoftwareDto> filterByCategory(){
        return null;
    }

    public List<SoftwareDto> filterBySegment(){
        return null;
    }

    public SoftwareDto update(int softwareId, SoftwareDto softwareDto){

        Software oldSoftware = this.softwareRepository.findById(softwareId).orElseThrow(() -> new ResourceNotFoundException("The expected software has not been found while updating the screenshots..."));

        oldSoftware.setTitle(softwareDto.getTitle());
        oldSoftware.setDescription(softwareDto.getDescription());
        oldSoftware.setLocation(softwareDto.getLocation());
        oldSoftware.setYearFounded(softwareDto.getYearFounded());
        oldSoftware.setLanguage(softwareDto.getLanguage());
        oldSoftware.setDifferenceFromOthers(softwareDto.getDifferenceFromOthers());
        oldSoftware.setProfileImageName(softwareDto.getProfileImageName());
        oldSoftware.setWebsiteLink(softwareDto.getWebsiteLink());
        oldSoftware.setTwitterId(softwareDto.getTwitterId());
        oldSoftware.setLinkedInId(softwareDto.getLinkedInId());
        oldSoftware.setFeatures(softwareDto.getFeatures());
        oldSoftware.setVideoName(softwareDto.getVideoName());
        oldSoftware.setScreenshots(softwareDto.getScreenshots());
        oldSoftware.setNoOfResponses(softwareDto.getNoOfResponses());
        oldSoftware.setRating(softwareDto.getRating());
        oldSoftware.setNotionDirectionRating(softwareDto.getNotionDirectionRating());
        oldSoftware.setEaseOfUseRating(softwareDto.getEaseOfUseRating());
        oldSoftware.setMeetsRequirementRating(softwareDto.getMeetsRequirementRating());
        oldSoftware.setQualitySupportRating(softwareDto.getQualitySupportRating());

        List<ReviewDto> reviewDtos = softwareDto.getReviewDtos();
        List<Review> reviews = reviewDtos.stream().map((reviewDto) -> this.modelMapper.map(reviewDto, Review.class)).collect(Collectors.toList());

        oldSoftware.setReviews(reviews);

        return this.modelMapper.map(this.softwareRepository.save(oldSoftware),SoftwareDto.class);
    }
    
}
