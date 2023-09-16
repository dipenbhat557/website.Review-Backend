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
import org.springframework.web.bind.annotation.RestController;

import com.websiteReview.Dtos.CategoryDto;
import com.websiteReview.Helper.CategoryRequest;
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

    // Create a new category
    @PostMapping
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryRequest categoryRequest) {
        return new ResponseEntity<CategoryDto>(this.categoryService.create(categoryRequest), HttpStatus.CREATED);
    }

    // Get a category by its ID
    @GetMapping("/{categoryId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable int categoryId) {
        return new ResponseEntity<>(this.categoryService.viewById(categoryId), HttpStatus.OK);
    }

    // Get all categories
    @GetMapping
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return new ResponseEntity<List<CategoryDto>>(this.categoryService.viewAll(), HttpStatus.OK);
    }

    // Delete a category by its ID
    @DeleteMapping("/{categoryId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<String> deleteCategory(@PathVariable int categoryId) {
        this.categoryService.delete(categoryId);
        return new ResponseEntity<String>("Category deleted successfully.......", HttpStatus.OK);
    }

    // Get categories associated with a subcategory by subcategory ID
    @GetMapping("/subCategory/{subCategoryId}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<List<CategoryDto>> viewCategoriesBySubCategory(@PathVariable int subCategoryId) {
        return new ResponseEntity<List<CategoryDto>>(this.categoryService.viewBySubCategories(subCategoryId),
                HttpStatus.ACCEPTED);
    }
}
