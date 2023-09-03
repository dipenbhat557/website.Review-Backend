package com.websiteReview.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.websiteReview.Dtos.SubCategoryDto;
import com.websiteReview.Exception.ResourceNotFoundException;
import com.websiteReview.Helper.ModelToDto;
import com.websiteReview.Helper.SubCategoryRequest;
import com.websiteReview.Helper.SubCategoryResponse;
import com.websiteReview.Model.Category;
import com.websiteReview.Model.SubCategory;
import com.websiteReview.Respository.CategoryRepository;
import com.websiteReview.Respository.SubCategoryRepository;
import com.websiteReview.Service.SubCategoryService;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private ModelToDto ModelToDto;

    @Override
    public SubCategoryDto create(SubCategoryRequest subCategoryRequest) {

        SubCategory subCategory = new SubCategory();
        subCategory.setTitle(subCategoryRequest.getTitle());

        List<Category> categories = new ArrayList<>();
        for (Category category : subCategoryRequest.getCategories()) {
            List<SubCategory> subCategories = category.getSubCategories();
            subCategories.add(subCategory);
            category.setSubCategories(subCategories);
            this.categoryRepository.save(category);

            categories.add(category);
        }
        subCategory.setCategories(categories);

        subCategory = this.subCategoryRepository.save(subCategory);
        return ModelToDto.subCategoryDto(subCategory);
    }

    @Override
    public SubCategoryDto viewById(int subCategoryId) {
        SubCategory subCategory = this.subCategoryRepository.findById(subCategoryId)
                .orElseThrow(() -> new ResourceNotFoundException("The expected sub category was not found."));
        return ModelToDto.subCategoryDto(subCategory);
    }

    @Override
    public List<SubCategoryDto> viewAll() {
        List<SubCategory> subCategories = this.subCategoryRepository.findAll();
        List<SubCategoryDto> subCategoryDtos = subCategories.stream()
                .map(subCategory -> ModelToDto.subCategoryDto(subCategory))
                .collect(Collectors.toList());
        return subCategoryDtos;
    }

    @Override
    public void delete(int subCategoryId) {
        SubCategory subCategory = this.subCategoryRepository.findById(subCategoryId)
                .orElseThrow(() -> new ResourceNotFoundException("The expected sub category was not found."));
        this.subCategoryRepository.delete(subCategory);
    }

    @Override
    public SubCategoryResponse viewByCategory(int categoryId, int pageSize, int pageNumber) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("The expected category is not found"));

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<SubCategory> page = this.subCategoryRepository.findByCategories(category, pageable);
        List<SubCategory> subCategories = page.getContent();
        List<SubCategoryDto> subCategoryDtos = subCategories.stream()
                .map(subCategory -> ModelToDto.subCategoryDto(subCategory))
                .collect(Collectors.toList());

        SubCategoryResponse response = new SubCategoryResponse();
        response.setContent(subCategoryDtos);
        response.setPageNumber(page.getNumber());
        response.setPageSize(page.getSize());
        response.setTotalPages(page.getTotalPages());
        response.setLastPage(page.isLast());

        return response;
    }

}
