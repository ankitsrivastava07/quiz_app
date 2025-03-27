package com.quiz.controller;

import com.quiz.dto.CategoryDto;
import com.quiz.dto.SubCategoryRequestDto;
import com.quiz.service.MakerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("api/v1/maker")
@CrossOrigin(origins = "http://localhost:4200")
public class MakerController {

    @Autowired
    private MakerService makerService;

    @PostMapping("/category/create")
    public ResponseEntity<?> createCategory(@RequestBody CategoryDto categoryDto) {
        return new ResponseEntity<>(makerService.createCategoryEntity(categoryDto), HttpStatus.CREATED);
    }

    @PostMapping("/category/{categoryId}/subcategory/create")
    public ResponseEntity<?> createSubcategory(@PathVariable String categoryId,
                                               @RequestBody CategoryDto categoryDto) {
        return new ResponseEntity<>(makerService.createSubcategory(categoryId, categoryDto), HttpStatus.OK);
    }

    @PostMapping("/category/{categoryId}/subcategory/{subcategoryId}")
    public ResponseEntity<?> createSubcategoryToSubcategory(
            @PathVariable String categoryId, @PathVariable String subcategoryId,
            @RequestBody SubCategoryRequestDto subCategoryRequestDto) {
        return new ResponseEntity<>(makerService.saveSubcategoryToSubcategory(subCategoryRequestDto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllCategories() {
        return new ResponseEntity<>(makerService.getAllCategories(), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        return new ResponseEntity<>(makerService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<?> getSubCategoryById(@PathVariable String categoryId) {
        makerService.findById(categoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
