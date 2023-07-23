package com.websiteReview.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websiteReview.Dtos.FeaturesDto;
import com.websiteReview.Exception.ResourceNotFoundException;
import com.websiteReview.Model.Features;
import com.websiteReview.Model.Software;
import com.websiteReview.Respository.FeatureRepository;
import com.websiteReview.Respository.SoftwareRepository;

@Service
public class FeatureService {

    @Autowired
    private FeatureRepository featureRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SoftwareRepository softwareRepository;

    public FeaturesDto createFeature(FeaturesDto featuresDto){
        Features features = this.modelMapper.map(featuresDto, Features.class);
        features = this.featureRepository.save(features);
        return this.modelMapper.map(features, FeaturesDto.class);
    }

    public List<FeaturesDto> getAllFeatures(){
        List<Features> features = this.featureRepository.findAll();
        List<FeaturesDto> featuresDtos = features.stream().map((feature) -> this.modelMapper.map(feature, FeaturesDto.class)).collect(Collectors.toList());
        return featuresDtos;
    }

    public List<FeaturesDto> getFeaturesBySoftware(int softwareId){
        Software software = this.softwareRepository.findById(softwareId).orElseThrow(() -> new ResourceNotFoundException("The expected software is not found while trying to fetch features by software"));
        List<Features> features = this.featureRepository.findBySoftware(software);
        List<FeaturesDto> featuresDto = features.stream().map((feature) -> this.modelMapper.map(feature,FeaturesDto.class)).collect(Collectors.toList());
        return featuresDto;
    }

    public FeaturesDto getById(int featureId){
        Features features = this.featureRepository.findById(featureId).orElseThrow(() -> new ResourceNotFoundException("The expected feature is not found"));
        return this.modelMapper.map(features, FeaturesDto.class);
    }
}
