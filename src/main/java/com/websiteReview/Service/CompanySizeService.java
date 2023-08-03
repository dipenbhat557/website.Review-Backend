package com.websiteReview.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websiteReview.Dtos.CompanySizeDto;
import com.websiteReview.Exception.ResourceNotFoundException;
import com.websiteReview.Model.CompanySize;
import com.websiteReview.Respository.CompanySizeRepository;

@Service
public class CompanySizeService {

    @Autowired
    private CompanySizeRepository companySizeRepository;

    @Autowired
    private ModelMapper modelMapper;

    public CompanySizeDto createCompanySize(CompanySizeDto companySizeDto){
        CompanySize companySize = this.modelMapper.map(companySizeDto, CompanySize.class);
        return this.modelMapper.map(companySize, CompanySizeDto.class);
    }

    public CompanySizeDto viewSizeById(int sizeId){
        CompanySize companySize = this.companySizeRepository.findById(sizeId).orElseThrow(() -> new ResourceNotFoundException("The expected company size is not found"));
        return this.modelMapper.map(companySize, CompanySizeDto.class);
    }

    public void deleteSize(int sizeId){
        CompanySize companySize = this.companySizeRepository.findById(sizeId).orElseThrow(() -> new ResourceNotFoundException("The expected company size is not found"));
        this.companySizeRepository.delete(companySize);
    }

    public List<CompanySizeDto> viewAll(){
        List<CompanySize> companySizes = this.companySizeRepository.findAll();
        List<CompanySizeDto> companySizeDtos = companySizes.stream().map(size -> this.modelMapper.map(size, CompanySizeDto.class)).collect(Collectors.toList());
        return companySizeDtos;
    }
}
