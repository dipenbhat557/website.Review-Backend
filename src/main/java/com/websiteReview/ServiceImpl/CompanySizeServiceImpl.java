package com.websiteReview.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websiteReview.Dtos.CompanySizeDto;
import com.websiteReview.Exception.ResourceNotFoundException;
import com.websiteReview.Helper.CompanySizeRequest;
import com.websiteReview.Helper.DtoToModel;
import com.websiteReview.Helper.ModelToDto;
import com.websiteReview.Model.CompanySize;
import com.websiteReview.Respository.CompanySizeRepository;
import com.websiteReview.Service.CompanySizeService;

@Service
public class CompanySizeServiceImpl implements CompanySizeService {

    @Autowired
    private CompanySizeRepository companySizeRepository;

    @Autowired
    private DtoToModel DtoToModel;

    @Autowired
    private ModelToDto ModelToDto;

    @Override
    public CompanySizeDto create(CompanySizeRequest companySizeRequest) {
        CompanySize companySize = new CompanySize();
        companySize.setTitle(companySizeRequest.getTitle());
        companySize = this.companySizeRepository.save(companySize);
        return ModelToDto.companySizeDto(companySize);
    }

    @Override
    public CompanySizeDto viewById(int sizeId) {
        CompanySize companySize = this.companySizeRepository.findById(sizeId)
                .orElseThrow(() -> new ResourceNotFoundException("The expected company size is not found"));

        CompanySizeDto companySizeDto = ModelToDto.companySizeDto(companySize);
        return companySizeDto;
    }

    @Override
    public void delete(int sizeId) {
        CompanySize companySize = this.companySizeRepository.findById(sizeId)
                .orElseThrow(() -> new ResourceNotFoundException("The expected company size is not found"));
        this.companySizeRepository.delete(companySize);
    }

    @Override
    public List<CompanySizeDto> viewAll() {
        List<CompanySize> companySizes = this.companySizeRepository.findAll();
        List<CompanySizeDto> companySizeDtos = companySizes.stream()
                .map(size -> ModelToDto.companySizeDto(size)).collect(Collectors.toList());
        return companySizeDtos;
    }
}
