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
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Autowired
    private SubCategoryServiceImpl subCategoryService;

    @PostMapping("/create")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<CategoryDto> createCatgory(@RequestBody CategoryRequest categoryRequest) {
        return new ResponseEntity<CategoryDto>(this.categoryService.create(categoryRequest), HttpStatus.CREATED);
    }

    @GetMapping("/viewById/{categoryId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<CategoryDto> getById(@PathVariable int categoryId) {
        return new ResponseEntity<>(this.categoryService.viewById(categoryId), HttpStatus.OK);
    }

    @GetMapping("/viewAll")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return new ResponseEntity<List<CategoryDto>>(this.categoryService.viewAll(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{categoryId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<String> deleteCategory(@PathVariable int categoryId) {
        this.categoryService.delete(categoryId);
        return new ResponseEntity<String>("Message deleted successfully.......", HttpStatus.OK);
    }

    @GetMapping("/viewBySubCategory/{subCategoryId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<List<CategoryDto>> viewBySubCategories(@PathVariable int subCategoryId) {
        return new ResponseEntity<List<CategoryDto>>(this.categoryService.viewBySubCategories(subCategoryId),
                HttpStatus.ACCEPTED);
    }

    // sub category started
    @PostMapping("/sub/create/{categoryId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<SubCategoryDto> createSubCategory(@RequestBody SubCategoryRequest subCategoryRequest) {
        return new ResponseEntity<SubCategoryDto>(this.subCategoryService.create(subCategoryRequest),
                HttpStatus.CREATED);
    }

    @GetMapping("/sub/viewById/{subCategoryId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<SubCategoryDto> viewBySubCategoryId(@PathVariable int subCategoryId) {
        return new ResponseEntity<SubCategoryDto>(this.subCategoryService.viewById(subCategoryId),
                HttpStatus.OK);
    }

    @GetMapping("/sub/viewAll")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<List<SubCategoryDto>> viewAllSubCategories() {
        return new ResponseEntity<List<SubCategoryDto>>(this.subCategoryService.viewAll(),
                HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/sub/delete/{subCategoryId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<String> deleteSubCategory(@PathVariable int subCategoryId) {
        this.subCategoryService.delete(subCategoryId);
        return new ResponseEntity<String>("Successfully deleted......", HttpStatus.OK);
    }

    @GetMapping("/sub/viewByCategory/{categoryId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<SubCategoryResponse> viewAllCategoriesByCategory(@PathVariable int categoryId,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.pageSizeString, required = false) int pageSize,
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.pageNumberString, required = false) int pageNumber) {
        return new ResponseEntity<SubCategoryResponse>(
                this.subCategoryService.viewByCategory(categoryId, pageSize, pageNumber),
                HttpStatus.OK);
    }

}
