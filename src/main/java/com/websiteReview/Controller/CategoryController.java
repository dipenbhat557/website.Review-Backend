package com.websiteReview.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.websiteReview.Dtos.CategoryDto;
import com.websiteReview.Dtos.SubCategoryDto;
import com.websiteReview.Service.CategoryService;
import com.websiteReview.Service.SubCategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SubCategoryService subCategoryService;

    @PostMapping("/create")
    public ResponseEntity<CategoryDto> createCatgory(@RequestBody CategoryDto categoryDto){
        return new ResponseEntity<CategoryDto>(this.categoryService.createCategory(categoryDto), HttpStatus.CREATED);
    }

    @GetMapping("/viewById/{categoryId}")
    public ResponseEntity<CategoryDto> getById(@PathVariable int categoryId){
        return new ResponseEntity<>(this.categoryService.getById(categoryId), HttpStatus.OK);
    }

    @GetMapping("/viewAll")
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        return new ResponseEntity<List<CategoryDto>>(this.categoryService.getAllCategories(), HttpStatus.OK);
    }

    @GetMapping("/delete/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable int categoryId){
        this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity<String>("Message deleted successfully.......", HttpStatus.OK);
    }

    @GetMapping("/viewBySubCategory/{subCategoryId}")
    public ResponseEntity<List<CategoryDto>> viewBySubCategories(@PathVariable int subCategoryId){
        return new ResponseEntity<List<CategoryDto>>(this.categoryService.getCategoriesBySubCategories(subCategoryId), HttpStatus.ACCEPTED);
    }



    @PostMapping("/sub/create")
    public ResponseEntity<SubCategoryDto> createSubCategory(@RequestBody SubCategoryDto subCategoryDto){
        return new ResponseEntity<SubCategoryDto>(this.subCategoryService.createSubCategory(subCategoryDto), HttpStatus.CREATED);
    }

    @GetMapping("/sub/viewById/{subCategoryId}")
    public ResponseEntity<SubCategoryDto> viewBySubCategoryId(@PathVariable int subCategoryId){
        return new ResponseEntity<SubCategoryDto>(this.subCategoryService.getSubCategoryById(subCategoryId), HttpStatus.OK);
    }

    @GetMapping("/sub/viewAll")
    public ResponseEntity<List<SubCategoryDto>> viewAllSubCategories(){
        return new ResponseEntity<List<SubCategoryDto>>(this.subCategoryService.getAllSubCategories(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/sub/delete/{subCategoryId}")
    public ResponseEntity<String> deleteSubCategory(@PathVariable int subCategoryId){
        this.subCategoryService.deleteSubCategory(subCategoryId);
        return new ResponseEntity<String>("Successfully deleted......", HttpStatus.OK);
    }

    @GetMapping("/sub/viewByCategory/{categoryId}")
    public ResponseEntity<List<SubCategoryDto>> viewAllCategoriesByCategory(@PathVariable int categoryId){
        return new ResponseEntity<List<SubCategoryDto>>(this.subCategoryService.getSubCategoriesByCategory(categoryId), HttpStatus.OK);
    }

}
