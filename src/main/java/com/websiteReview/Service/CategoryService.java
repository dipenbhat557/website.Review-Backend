package com.websiteReview.Service;

import java.util.List;

import com.websiteReview.Dtos.CategoryDto;

public interface CategoryService {

    public CategoryDto create(CategoryDto categoryDto);

    public CategoryDto viewById(int categoryId);

    public List<CategoryDto> viewAll();

    public void delete(int categoryId);

    public List<CategoryDto> viewBySubCategories(int subCategoryId);
}
