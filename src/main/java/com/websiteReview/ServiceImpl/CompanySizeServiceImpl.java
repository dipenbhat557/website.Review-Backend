package com.websiteReview.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websiteReview.Dtos.CompanySizeDto;
import com.websiteReview.Exception.ResourceNotFoundException;
import com.websiteReview.Helper.CompanySizeRequest;
import com.websiteReview.Helper.ModelToDto;
import com.websiteReview.Model.CompanySize;
import com.websiteReview.Respository.CompanySizeRepository;
import com.websiteReview.Service.CompanySizeService;

@Service
public class CompanySizeServiceImpl implements CompanySizeService {

    @Autowired
    private CompanySizeRepository companySizeRepository;

    @Autowired
    private ModelToDto ModelToDto;

    @Override
    public CompanySizeDto create(CompanySizeRequest companySizeRequest) {
        // Create a new CompanySize instance and set its properties
        CompanySize companySize = new CompanySize();
        companySize.setTitle(companySizeRequest.getTitle());

        // Save the CompanySize entity to the database
        companySize = this.companySizeRepository.save(companySize);

        // Convert and return the saved CompanySize as a DTO
        CompanySizeDto companySizeDto = ModelToDto.companySizeDto(companySize);
        return companySizeDto;
    }

    @Override
    public CompanySizeDto viewById(int sizeId) {
        // Find the CompanySize by its ID or throw an exception if not found
        CompanySize companySize = this.companySizeRepository.findById(sizeId)
                .orElseThrow(() -> new ResourceNotFoundException("The expected company size is not found"));

        // Convert and return the CompanySize as a DTO
        CompanySizeDto companySizeDto = ModelToDto.companySizeDto(companySize);
        return companySizeDto;
    }

    @Override
    public void delete(int sizeId) {
        // Find the CompanySize by its ID or throw an exception if not found
        CompanySize companySize = this.companySizeRepository.findById(sizeId)
                .orElseThrow(() -> new ResourceNotFoundException("The expected company size is not found"));

        // Delete the CompanySize from the database
        this.companySizeRepository.delete(companySize);
    }

    @Override
    public List<CompanySizeDto> viewAll() {
        // Retrieve all CompanySizes from the repository
        List<CompanySize> companySizes = this.companySizeRepository.findAll();

        // Convert the list of CompanySizes to a list of DTOs
        List<CompanySizeDto> companySizeDtos = companySizes.stream()
                .map(size -> ModelToDto.companySizeDto(size))
                .collect(Collectors.toList());

        return companySizeDtos;
    }
}
