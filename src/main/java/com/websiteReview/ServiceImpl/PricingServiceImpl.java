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
        // Create a new Pricing object and populate it with data from the request
        Pricing pricing = new Pricing();
        pricing.setTitle(pricingRequest.getTitle());
        pricing.setPrice(pricingRequest.getPrice());
        pricing.setFeatures(pricingRequest.getFeatures());

        // Find the corresponding Software by its ID
        Software software = this.softwareRepository.findById(softwareId)
                .orElseThrow(() -> new ResourceNotFoundException("The expected software is not found"));

        // Set the Software for this Pricing
        pricing.setSoftware(software);

        // Save the Pricing entity to the database
        pricing = this.pricingRepository.save(pricing);

        // Return the Pricing DTO
        return ModelToDto.pricingDto(pricing);
    }

    @Override
    public PricingDto viewById(int pricingId) {
        // Find the Pricing by its ID
        Pricing pricing = this.pricingRepository.findById(pricingId)
                .orElseThrow(() -> new ResourceNotFoundException("The expected pricing is not found"));

        // Convert the Pricing entity to a Pricing DTO and return it
        return ModelToDto.pricingDto(pricing);
    }

    @Override
    public void delete(int pricingId) {
        // Find the Pricing by its ID
        Pricing pricing = this.pricingRepository.findById(pricingId)
                .orElseThrow(() -> new ResourceNotFoundException("The expected pricing is not found"));

        // Delete the Pricing from the database
        this.pricingRepository.delete(pricing);
    }

    @Override
    public List<PricingDto> viewBySoftware(int softwareId) {
        // Find the Software by its ID
        Software software = this.softwareRepository.findById(softwareId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "The expected software is not found while fetching pricing"));

        // Retrieve the list of Pricings associated with the Software
        List<Pricing> pricings = this.pricingRepository.findBySoftware(software);

        // Convert the list of Pricing entities to a list of Pricing DTOs and return it
        List<PricingDto> pricingDtos = pricings.stream().map(pricing -> ModelToDto.pricingDto(pricing))
                .collect(Collectors.toList());
        return pricingDtos;
    }
}
