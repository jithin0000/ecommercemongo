package com.jithin.ecommerce.controller;

import com.jithin.ecommerce.model.Category;
import com.jithin.ecommerce.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<?> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAll());
    }

    @PostMapping("")
    public ResponseEntity<?> create(@Valid @RequestBody Category body)  {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.create(body));
    }
}
