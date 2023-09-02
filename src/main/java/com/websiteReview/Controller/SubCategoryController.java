package com.websiteReview.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.websiteReview.Dtos.CategoryDto;
import com.websiteReview.Dtos.SubCategoryDto;
import com.websiteReview.Helper.AppConstants;
import com.websiteReview.Helper.CategoryRequest;
import com.websiteReview.Helper.SubCategoryRequest;
import com.websiteReview.Helper.SubCategoryResponse;
import com.websiteReview.ServiceImpl.CategoryServiceImpl;
import com.websiteReview.ServiceImpl.SubCategoryServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/subCategory")
public class SubCategoryController {

    @Autowired
    private SubCategoryServiceImpl subCategoryService;

    // sub category started
    @PostMapping("/{categoryId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<SubCategoryDto> createSubCategory(@RequestBody SubCategoryRequest subCategoryRequest) {
        return new ResponseEntity<SubCategoryDto>(this.subCategoryService.create(subCategoryRequest),
                HttpStatus.CREATED);
    }

    @GetMapping("/{subCategoryId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<SubCategoryDto> viewBySubCategoryId(@PathVariable int subCategoryId) {
        return new ResponseEntity<SubCategoryDto>(this.subCategoryService.viewById(subCategoryId),
                HttpStatus.OK);
    }

    @GetMapping
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<List<SubCategoryDto>> viewAllSubCategories() {
        return new ResponseEntity<List<SubCategoryDto>>(this.subCategoryService.viewAll(),
                HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{subCategoryId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<String> deleteSubCategory(@PathVariable int subCategoryId) {
        this.subCategoryService.delete(subCategoryId);
        return new ResponseEntity<String>("Successfully deleted......", HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<SubCategoryResponse> viewAllCategoriesByCategory(@PathVariable int categoryId,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.pageSizeString, required = false) int pageSize,
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.pageNumberString, required = false) int pageNumber) {
        return new ResponseEntity<SubCategoryResponse>(
                this.subCategoryService.viewByCategory(categoryId, pageSize, pageNumber),
                HttpStatus.OK);
    }

}
