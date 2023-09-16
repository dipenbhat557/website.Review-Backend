package com.websiteReview.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websiteReview.Dtos.CategoryDto;
import com.websiteReview.Exception.ResourceNotFoundException;
import com.websiteReview.Helper.CategoryRequest;
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

    @Autowired
    private ModelToDto ModelToDto;

    @Override
    public CategoryDto create(CategoryRequest categoryRequest) {
        // Create a new Category instance and set its title from the request
        Category category = new Category();
        category.setTitle(categoryRequest.getTitle());

        // Save the category to the database
        CategoryDto categoryDto = ModelToDto.categoryDto(this.categoryRepository.save(category));

        return categoryDto;
    }

    @Override
    public CategoryDto viewById(int categoryId) {
        // Retrieve the category by its ID or throw an exception if not found
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("The expected category was not found."));

        // Convert and return the category as a DTO
        return ModelToDto.categoryDto(category);
    }

    @Override
    public List<CategoryDto> viewAll() {
        // Retrieve all categories from the database
        List<Category> categories = this.categoryRepository.findAll();

        // Convert the list of categories to a list of DTOs
        List<CategoryDto> categoryDtos = categories.stream()
                .map(category -> ModelToDto.categoryDto(category)).collect(Collectors.toList());

        return categoryDtos;
    }

    @Override
    public void delete(int categoryId) {
        // Retrieve the category by its ID or throw an exception if not found
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("The expected category was not found."));

        // Delete the category from the database
        this.categoryRepository.delete(category);
    }

    @Override
    public List<CategoryDto> viewBySubCategories(int subCategoryId) {
        // Retrieve the subcategory by its ID or throw an exception if not found
        SubCategory subCategory = this.subCategoryRepository.findById(subCategoryId)
                .orElseThrow(() -> new ResourceNotFoundException("The expected sub category is not found"));

        // Find categories associated with the subcategory
        List<Category> categories = this.categoryRepository.findBySubCategories(subCategory);

        // Convert the list of categories to a list of DTOs
        List<CategoryDto> categoryDtos = categories.stream()
                .map(category -> ModelToDto.categoryDto(category)).collect(Collectors.toList());

        return categoryDtos;
    }
}
