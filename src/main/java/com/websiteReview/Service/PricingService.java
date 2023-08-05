package com.websiteReview.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websiteReview.Dtos.PricingDto;
import com.websiteReview.Exception.ResourceNotFoundException;
import com.websiteReview.Model.Pricing;
import com.websiteReview.Model.Software;
import com.websiteReview.Respository.PricingRepository;
import com.websiteReview.Respository.SoftwareRepository;

@Service
public class PricingService {

    @Autowired
    private PricingRepository pricingRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SoftwareRepository softwareRepository;

    public PricingDto create(PricingDto pricingDto) {
        Pricing pricing = this.modelMapper.map(pricingDto, Pricing.class);
        pricing = this.pricingRepository.save(pricing);
        return this.modelMapper.map(pricing, PricingDto.class);
    }

    public PricingDto viewById(int pricingId) {
        Pricing pricing = this.pricingRepository.findById(pricingId)
                .orElseThrow(() -> new ResourceNotFoundException("The expected pricing is not found"));
        return this.modelMapper.map(pricing, PricingDto.class);
    }

    public void delete(int pricingId) {
        Pricing pricing = this.pricingRepository.findById(pricingId)
                .orElseThrow(() -> new ResourceNotFoundException("The expected pricing is not found"));
        this.pricingRepository.delete(pricing);
    }

    public List<PricingDto> viewBySoftware(int softwareId) {
        Software software = this.softwareRepository.findById(softwareId).orElseThrow(
                () -> new ResourceNotFoundException("The expected software is not found while fetching pricing"));
        List<Pricing> pricings = this.pricingRepository.findBySoftware(software);
        List<PricingDto> pricingDtos = pricings.stream().map(pricing -> this.modelMapper.map(pricing, PricingDto.class))
                .collect(Collectors.toList());
        return pricingDtos;
    }
}
