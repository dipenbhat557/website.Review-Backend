package com.websiteReview.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.websiteReview.Dtos.SubCategoryDto;
import com.websiteReview.Exception.ResourceNotFoundException;
import com.websiteReview.Helper.SubCategoryResponse;
import com.websiteReview.Model.Category;
import com.websiteReview.Model.SubCategory;
import com.websiteReview.Respository.CategoryRepository;
import com.websiteReview.Respository.SubCategoryRepository;

@Service
public class SubCategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    public SubCategoryDto create(SubCategoryDto subCategoryDto){
        SubCategory subCategory = this.modelMapper.map(subCategoryDto, SubCategory.class);
        subCategory = this.subCategoryRepository.save(subCategory);
        return this.modelMapper.map(subCategory, SubCategoryDto.class);
    }

    public SubCategoryDto viewById(int subCategoryId){
        SubCategory subCategory = this.subCategoryRepository.findById(subCategoryId).orElseThrow(() -> new ResourceNotFoundException("The expected sub category was not found."));
        return this.modelMapper.map(subCategory, SubCategoryDto.class);
    }

    public List<SubCategoryDto> viewAll(){
        List<SubCategory>  subCategories = this.subCategoryRepository.findAll();
        List<SubCategoryDto> subCategoryDtos = subCategories.stream().map(subCategory -> this.modelMapper.map(subCategory, SubCategoryDto.class)).collect(Collectors.toList());
        return subCategoryDtos;
    }

    public void delete(int subCategoryId){
        SubCategory subCategory = this.subCategoryRepository.findById(subCategoryId).orElseThrow(() -> new ResourceNotFoundException("The expected sub category was not found."));
        this.subCategoryRepository.delete(subCategory);
    }

    public SubCategoryResponse viewByCategory(int categoryId, int pageSize,int pageNumber){
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("The expected category is not found"));

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<SubCategory> page = this.subCategoryRepository.findByCategories(category,pageable);
        List<SubCategory> subCategories = page.getContent();
        List<SubCategoryDto> subCategoryDtos = subCategories.stream().map(subCategory -> this.modelMapper.map(subCategory, SubCategoryDto.class)).collect(Collectors.toList());

        SubCategoryResponse response = new SubCategoryResponse();
        response.setContent(subCategoryDtos);
        response.setPageNumber(page.getNumber());
        response.setPageSize(page.getSize());
        response.setTotalPages(page.getTotalPages());
        response.setLastPage(page.isLast());

        return response;
    }


}
