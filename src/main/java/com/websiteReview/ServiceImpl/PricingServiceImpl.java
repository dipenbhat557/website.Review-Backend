package com.websiteReview.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websiteReview.Dtos.PricingDto;
import com.websiteReview.Exception.ResourceNotFoundException;
import com.websiteReview.Helper.ModelToDto;
import com.websiteReview.Helper.PricingRequest;
import com.websiteReview.Model.Pricing;
import com.websiteReview.Model.Software;
import com.websiteReview.Respository.PricingRepository;
import com.websiteReview.Respository.SoftwareRepository;
import com.websiteReview.Service.PricingService;

@Service
public class PricingServiceImpl implements PricingService {

    @Autowired
    private PricingRepository pricingRepository;

    @Autowired
    private SoftwareRepository softwareRepository;

    @Autowired
    private ModelToDto ModelToDto;

    @Override
    public PricingDto create(PricingRequest pricingRequest, int softwareId) {

        Pricing pricing = new Pricing();
        pricing.setTitle(pricingRequest.getTitle());
        pricing.setPrice(pricingRequest.getPrice());
        pricing.setFeatures(pricingRequest.getFeatures());

        Software software = this.softwareRepository.findById(softwareId)
                .orElseThrow(() -> new ResourceNotFoundException("The expected software is not found"));

        pricing.setSoftware(software);
        pricing = this.pricingRepository.save(pricing);
        // List<Pricing> pricings = software.getPricings();
        // pricings.add(pricing);
        // this.softwareRepository.save(software);
        return ModelToDto.pricingDto(pricing);
    }

    @Override
    public PricingDto viewById(int pricingId) {
        Pricing pricing = this.pricingRepository.findById(pricingId)
                .orElseThrow(() -> new ResourceNotFoundException("The expected pricing is not found"));
        return ModelToDto.pricingDto(pricing);
    }

    @Override
    public void delete(int pricingId) {
        Pricing pricing = this.pricingRepository.findById(pricingId)
                .orElseThrow(() -> new ResourceNotFoundException("The expected pricing is not found"));
        this.pricingRepository.delete(pricing);
    }

    @Override
    public List<PricingDto> viewBySoftware(int softwareId) {
        Software software = this.softwareRepository.findById(softwareId).orElseThrow(
                () -> new ResourceNotFoundException("The expected software is not found while fetching pricing"));
        List<Pricing> pricings = this.pricingRepository.findBySoftware(software);
        List<PricingDto> pricingDtos = pricings.stream().map(pricing -> ModelToDto.pricingDto(pricing))
                .collect(Collectors.toList());
        return pricingDtos;
    }
}
