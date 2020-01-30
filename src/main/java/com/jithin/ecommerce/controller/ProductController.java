package com.jithin.ecommerce.controller;

import com.jithin.ecommerce.model.Category;
import com.jithin.ecommerce.model.Product;
import com.jithin.ecommerce.services.CategoryService;
import com.jithin.ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<?> getAllProducts(){
        return ResponseEntity.ok(productService.getAll());
    }

    @PostMapping("")
    public ResponseEntity<?> create(@Valid @RequestBody Product body)  {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(body));
    }
}
