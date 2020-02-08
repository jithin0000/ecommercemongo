package com.jithin.ecommerce.controller;

import com.jithin.ecommerce.exceptions.CategoryNotFoundException;
import com.jithin.ecommerce.model.Category;
import com.jithin.ecommerce.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<?> getAllCategories(
            @RequestParam(value = "page", defaultValue = "0") String page,
            @RequestParam(value = "size", defaultValue = "10") String size,
            @RequestParam(value = "sort", defaultValue = "name") String sort,
            @RequestParam(value = "search", defaultValue = "") String search
    ) {

        Page<Category> categories = categoryService.getPaginatedFilterCategories(
                Integer.parseInt(page),
                Integer.parseInt(size),
                sort,
                search);
        return ResponseEntity.ok(
                categories
        );
    }

    @PostMapping("")
    public ResponseEntity<?> create(@Valid @RequestBody Category body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.create(body));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        Category category = categoryService.getById(id).orElseThrow(
                () -> new CategoryNotFoundException(id)
        );

        return ResponseEntity.ok(category);
    }


}
