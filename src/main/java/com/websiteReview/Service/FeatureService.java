package com.websiteReview.Service;

import java.util.List;

import com.websiteReview.Dtos.FeaturesDto;

public interface FeatureService {

    public FeaturesDto create(FeaturesDto featuresDto);

    public List<FeaturesDto> viewAll();

    public FeaturesDto viewById(int featureId);

    public void delete(int featureId);
}
