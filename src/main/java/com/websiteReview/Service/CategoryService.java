package com.websiteReview.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websiteReview.Dtos.CategoryDto;
import com.websiteReview.Dtos.SubCategoryDto;
import com.websiteReview.Exception.ResourceNotFoundException;
import com.websiteReview.Model.Category;
import com.websiteReview.Model.SubCategory;
import com.websiteReview.Respository.CategoryRepository;
import com.websiteReview.Respository.SubCategoryRepository;

@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    public CategoryDto createCategory(CategoryDto categoryDto){

        Category category = this.modelMapper.map(categoryDto, Category.class);
        // List<SubCategoryDto> subCategoryDto = categoryDto.getSubCategoryDtos();
        // List<SubCategory>
        // category.setSubCategories(this.modelMapper.map(subCategoryDto, CategoryDto.class));
        categoryDto = this.modelMapper.map(this.categoryRepository.save(category), CategoryDto.class);
        return categoryDto;
    }

    public CategoryDto getById(int categoryId){
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("The expected category was not found."));
        return this.modelMapper.map(category, CategoryDto.class);
    }

    public List<CategoryDto> getAllCategories(){
        List<Category>  categories = this.categoryRepository.findAll();
        List<CategoryDto> categoryDtos = categories.stream().map(category -> this.modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
        return categoryDtos;
    }

    public void deleteCategory(int categoryId){
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("The expected category was not found."));
        this.categoryRepository.delete(category);
    }

    public List<CategoryDto> getCategoriesBySubCategories(int subCategoryId){
        SubCategory subCategory = this.subCategoryRepository.findById(subCategoryId).orElseThrow(() -> new ResourceNotFoundException("The expected sub category is not found"));
        List<Category> categories = this.categoryRepository.findBySubCategories(subCategory);
        List<CategoryDto> catgoryDtos = categories.stream().map(category -> this.modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
        return catgoryDtos;
    }

}
 