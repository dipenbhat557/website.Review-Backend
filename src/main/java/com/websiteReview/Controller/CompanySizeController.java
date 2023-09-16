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

import com.websiteReview.Dtos.CompanySizeDto;
import com.websiteReview.Helper.CompanySizeRequest;
import com.websiteReview.ServiceImpl.CompanySizeServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/company/size")
public class CompanySizeController {

    @Autowired
    private CompanySizeServiceImpl companySizeService;

    // Create a new company size
    @PostMapping
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<CompanySizeDto> createCompanySize(@RequestBody CompanySizeRequest companySizeRequest) {
        return new ResponseEntity<CompanySizeDto>(this.companySizeService.create(companySizeRequest),
                HttpStatus.CREATED);
    }

    // Get a company size by its ID
    @GetMapping("/{sizeId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<CompanySizeDto> getCompanySizeById(@PathVariable int sizeId) {
        return new ResponseEntity<CompanySizeDto>(this.companySizeService.viewById(sizeId), HttpStatus.OK);
    }

    // Delete a company size by its ID
    @DeleteMapping("/{sizeId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<String> deleteCompanySize(@PathVariable int sizeId) {
        this.companySizeService.delete(sizeId);
        return new ResponseEntity<String>("Deleted Successfully", HttpStatus.OK);
    }

    // Get all company sizes
    @GetMapping
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<List<CompanySizeDto>> getAllCompanySizes() {
        return new ResponseEntity<List<CompanySizeDto>>(this.companySizeService.viewAll(), HttpStatus.OK);
    }
}
