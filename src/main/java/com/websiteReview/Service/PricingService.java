package com.websiteReview.Service;

import java.util.List;

import com.websiteReview.Dtos.PricingDto;
import com.websiteReview.Helper.PricingRequest;

public interface PricingService {

    public PricingDto create(PricingRequest pricingRequest, int softwareId);

    public PricingDto viewById(int pricingId);

    public void delete(int pricingId);

    public List<PricingDto> viewBySoftware(int softwareId);
}
