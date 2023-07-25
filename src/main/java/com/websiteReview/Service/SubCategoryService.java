package com.websiteReview.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websiteReview.Dtos.SubCategoryDto;
import com.websiteReview.Exception.ResourceNotFoundException;
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

    public SubCategoryDto createSubCategory(SubCategoryDto subCategoryDto){
        SubCategory subCategory = this.modelMapper.map(subCategoryDto, SubCategory.class);
        subCategory = this.subCategoryRepository.save(subCategory);
        return this.modelMapper.map(subCategory, SubCategoryDto.class);
    }

    public SubCategoryDto getSubCategoryById(int subCategoryId){
        SubCategory subCategory = this.subCategoryRepository.findById(subCategoryId).orElseThrow(() -> new ResourceNotFoundException("The expected sub category was not found."));
        return this.modelMapper.map(subCategory, SubCategoryDto.class);
    }

    public List<SubCategoryDto> getAllSubCategories(){
        List<SubCategory>  subCategories = this.subCategoryRepository.findAll();
        List<SubCategoryDto> subCategoryDtos = subCategories.stream().map(subCategory -> this.modelMapper.map(subCategory, SubCategoryDto.class)).collect(Collectors.toList());
        return subCategoryDtos;
    }

    public void deleteSubCategory(int subCategoryId){
        SubCategory subCategory = this.subCategoryRepository.findById(subCategoryId).orElseThrow(() -> new ResourceNotFoundException("The expected sub category was not found."));
        this.subCategoryRepository.delete(subCategory);
    }

    public List<SubCategoryDto> getSubCategoriesByCategory(int categoryId){
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("The expected category is not found"));
        List<SubCategory> subCategories = this.subCategoryRepository.findByCategories(category);
        List<SubCategoryDto> subCategoryDtos = subCategories.stream().map(subCategory -> this.modelMapper.map(subCategory, SubCategoryDto.class)).collect(Collectors.toList());
        return subCategoryDtos;
    }


}
