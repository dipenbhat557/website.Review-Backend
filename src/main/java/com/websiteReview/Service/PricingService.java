package com.websiteReview.Service;

import java.util.List;

import com.websiteReview.Dtos.PricingDto;

public interface PricingService {

    public PricingDto create(PricingDto pricingDto);

    public PricingDto viewById(int pricingId);

    public void delete(int pricingId);

    public List<PricingDto> viewBySoftware(int softwareId);
}
