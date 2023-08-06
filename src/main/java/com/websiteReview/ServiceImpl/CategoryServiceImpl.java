package com.websiteReview.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websiteReview.Dtos.CategoryDto;
import com.websiteReview.Exception.ResourceNotFoundException;
import com.websiteReview.Helper.DtoToModel;
import com.websiteReview.Helper.ModelToDto;
import com.websiteReview.Model.Category;
import com.websiteReview.Model.SubCategory;
import com.websiteReview.Respository.CategoryRepository;
import com.websiteReview.Respository.SubCategoryRepository;
import com.websiteReview.Service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Override
    public CategoryDto create(CategoryDto categoryDto) {

        Category category = DtoToModel.category(categoryDto);

        categoryDto = ModelToDto.categoryDto(this.categoryRepository.save(category));

        return categoryDto;
    }

    @Override
    public CategoryDto viewById(int categoryId) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("The expected category was not found."));
        return ModelToDto.categoryDto(category);
    }

    @Override
    public List<CategoryDto> viewAll() {
        List<Category> categories = this.categoryRepository.findAll();
        List<CategoryDto> categoryDtos = categories.stream()
                .map(category -> ModelToDto.categoryDto(category)).collect(Collectors.toList());
        return categoryDtos;
    }

    @Override
    public void delete(int categoryId) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("The expected category was not found."));
        this.categoryRepository.delete(category);
    }

    @Override
    public List<CategoryDto> viewBySubCategories(int subCategoryId) {
        SubCategory subCategory = this.subCategoryRepository.findById(subCategoryId)
                .orElseThrow(() -> new ResourceNotFoundException("The expected sub category is not found"));
        List<Category> categories = this.categoryRepository.findBySubCategories(subCategory);
        List<CategoryDto> categoryDtos = categories.stream()
                .map(category -> ModelToDto.categoryDto(category)).collect(Collectors.toList());
        return categoryDtos;
    }

}
