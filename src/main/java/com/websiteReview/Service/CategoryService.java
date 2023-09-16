package com.websiteReview.Service;

import java.util.List;

import com.websiteReview.Dtos.CategoryDto;
import com.websiteReview.Helper.CategoryRequest;

public interface CategoryService {

    /**
     * Create a new category based on the provided CategoryRequest.
     *
     * @param categoryRequest The request containing information to create the
     *                        category.
     * @return The created CategoryDto.
     */
    public CategoryDto create(CategoryRequest categoryRequest);

    /**
     * View a category by its unique identifier.
     *
     * @param categoryId The unique ID of the category to view.
     * @return The CategoryDto representing the category.
     */
    public CategoryDto viewById(int categoryId);

    /**
     * View all categories.
     *
     * @return A list of CategoryDto representing all categories.
     */
    public List<CategoryDto> viewAll();

    /**
     * Delete a category by its unique identifier.
     *
     * @param categoryId The unique ID of the category to delete.
     */
    public void delete(int categoryId);

    /**
     * View categories based on their associated sub-categories.
     *
     * @param subCategoryId The unique ID of the sub-category to filter by.
     * @return A list of CategoryDto representing categories associated with the
     *         specified sub-category.
     */
    public List<CategoryDto> viewBySubCategories(int subCategoryId);
}
