package com.websiteReview.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.websiteReview.Dtos.PricingDto;
import com.websiteReview.Helper.PricingRequest;
import com.websiteReview.ServiceImpl.PricingServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/pricing")
public class PricingController {

    @Autowired
    private PricingServiceImpl pricingService;

    // Create a new pricing for a software
    @PostMapping("/{softwareId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<PricingDto> createPricing(@RequestBody PricingRequest pricingRequest,
            @PathVariable int softwareId) {
        return new ResponseEntity<PricingDto>(this.pricingService.create(pricingRequest, softwareId),
                HttpStatus.CREATED);
    }

    // Get pricing details by pricing ID
    @GetMapping("/{pricingId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<PricingDto> getById(@PathVariable int pricingId) {
        return new ResponseEntity<PricingDto>(this.pricingService.viewById(pricingId), HttpStatus.ACCEPTED);
    }

    // Get pricing details for a specific software
    @GetMapping("/software/{softwareId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<List<PricingDto>> getBySoftware(@PathVariable int softwareId) {
        return new ResponseEntity<List<PricingDto>>(this.pricingService.viewBySoftware(softwareId), HttpStatus.OK);
    }

    // Delete a pricing entry by pricing ID
    @DeleteMapping("/{pricingId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<String> deletePricing(@PathVariable int pricingId) {
        this.pricingService.delete(pricingId);
        return new ResponseEntity<String>("Deleted Successfully", HttpStatus.OK);
    }
}
