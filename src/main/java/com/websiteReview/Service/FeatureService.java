package com.websiteReview.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websiteReview.Dtos.FeaturesDto;
import com.websiteReview.Exception.ResourceNotFoundException;
import com.websiteReview.Model.Features;
import com.websiteReview.Respository.FeatureRepository;

@Service
public class FeatureService {

    @Autowired
    private FeatureRepository featureRepository;

    @Autowired
    private ModelMapper modelMapper;

    public FeaturesDto create(FeaturesDto featuresDto) {
        Features features = this.modelMapper.map(featuresDto, Features.class);
        features = this.featureRepository.save(features);
        return this.modelMapper.map(features, FeaturesDto.class);
    }

    public List<FeaturesDto> viewAll() {
        List<Features> features = this.featureRepository.findAll();
        List<FeaturesDto> featuresDtos = features.stream()
                .map((feature) -> this.modelMapper.map(feature, FeaturesDto.class)).collect(Collectors.toList());
        return featuresDtos;
    }

    public FeaturesDto viewById(int featureId) {
        Features features = this.featureRepository.findById(featureId)
                .orElseThrow(() -> new ResourceNotFoundException("The expected feature is not found"));
        return this.modelMapper.map(features, FeaturesDto.class);
    }

    public void delete(int featureId) {
        Features feature = this.featureRepository.findById(featureId)
                .orElseThrow(() -> new ResourceNotFoundException("The expected feature is not found"));
        this.featureRepository.delete(feature);
    }
}
