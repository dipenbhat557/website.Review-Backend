package com.websiteReview.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websiteReview.Dtos.FeaturesDto;
import com.websiteReview.Exception.ResourceNotFoundException;
import com.websiteReview.Helper.DtoToModel;
import com.websiteReview.Helper.ModelToDto;
import com.websiteReview.Model.Features;
import com.websiteReview.Respository.FeatureRepository;
import com.websiteReview.Service.FeatureService;

@Service
public class FeatureServiceImpl implements FeatureService {

    @Autowired
    private FeatureRepository featureRepository;

    @Override
    public FeaturesDto create(FeaturesDto featuresDto) {
        Features features = DtoToModel.features(featuresDto);
        features = this.featureRepository.save(features);
        return ModelToDto.featuresDto(features);
    }

    @Override
    public List<FeaturesDto> viewAll() {
        List<Features> features = this.featureRepository.findAll();
        List<FeaturesDto> featuresDtos = features.stream()
                .map(feature -> ModelToDto.featuresDto(feature)).collect(Collectors.toList());
        return featuresDtos;
    }

    @Override
    public FeaturesDto viewById(int featureId) {
        Features features = this.featureRepository.findById(featureId)
                .orElseThrow(() -> new ResourceNotFoundException("The expected feature is not found"));
        return ModelToDto.featuresDto(features);
    }

    @Override
    public void delete(int featureId) {
        Features feature = this.featureRepository.findById(featureId)
                .orElseThrow(() -> new ResourceNotFoundException("The expected feature is not found"));
        this.featureRepository.delete(feature);
    }
}
