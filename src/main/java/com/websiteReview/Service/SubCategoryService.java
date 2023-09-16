package com.websiteReview.Service;

import java.util.List;

import com.websiteReview.Dtos.SubCategoryDto;
import com.websiteReview.Helper.SubCategoryRequest;
import com.websiteReview.Helper.SubCategoryResponse;

public interface SubCategoryService {

    /**
     * Creates a new sub-category entry in the system.
     *
     * @param subCategoryRequest The sub-category details to create.
     * @return The created SubCategoryDto.
     */
    public SubCategoryDto create(SubCategoryRequest subCategoryRequest);

    /**
     * Retrieves a sub-category entry by its ID.
     *
     * @param subCategoryId The ID of the sub-category to retrieve.
     * @return The SubCategoryDto with the specified ID.
     */
    public SubCategoryDto viewById(int subCategoryId);

    /**
     * Retrieves all sub-category entries.
     *
     * @return A list of SubCategoryDto representing all sub-category entries.
     */
    public List<SubCategoryDto> viewAll();

    /**
     * Deletes a sub-category entry by its ID.
     *
     * @param subCategoryId The ID of the sub-category to delete.
     */
    public void delete(int subCategoryId);

    /**
     * Retrieves sub-category entries by category with pagination.
     *
     * @param categoryId The ID of the category for which to retrieve
     *                   sub-categories.
     * @param pageSize   The number of sub-category entries per page.
     * @param pageNumber The page number for pagination.
     * @return A SubCategoryResponse containing sub-category entries for the
     *         specified category.
     */
    public SubCategoryResponse viewByCategory(int categoryId, int pageSize, int pageNumber);
}
