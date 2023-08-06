package com.websiteReview.Service;

import java.util.List;

import com.websiteReview.Dtos.SubCategoryDto;
import com.websiteReview.Helper.SubCategoryResponse;

public interface SubCategoryService {

    public SubCategoryDto create(SubCategoryDto subCategoryDto);

    public SubCategoryDto viewById(int subCategoryId);

    public List<SubCategoryDto> viewAll();

    public void delete(int subCategoryId);

    public SubCategoryResponse viewByCategory(int categoryId, int pageSize, int pageNumber);

}
