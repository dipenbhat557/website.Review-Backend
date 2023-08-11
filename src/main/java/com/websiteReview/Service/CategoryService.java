package com.websiteReview.Service;

import java.util.List;

import com.websiteReview.Dtos.CategoryDto;
import com.websiteReview.Helper.CategoryRequest;

public interface CategoryService {

    public CategoryDto create(CategoryRequest categoryRequest);

    public CategoryDto viewById(int categoryId);

    public List<CategoryDto> viewAll();

    public void delete(int categoryId);

    public List<CategoryDto> viewBySubCategories(int subCategoryId);
}
