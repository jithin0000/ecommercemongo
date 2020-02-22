package com.jithin.ecommerce.controller;

import com.jithin.ecommerce.exceptions.ProductNotFoundException;
import com.jithin.ecommerce.model.Category;
import com.jithin.ecommerce.model.Product;
import com.jithin.ecommerce.model.ProductColor;
import com.jithin.ecommerce.services.CategoryService;
import com.jithin.ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/name")
    public ResponseEntity<?> getAllProductsByName(
            @RequestParam(value = "search", defaultValue = "") String search
    ) {
        return ResponseEntity.ok(productService.getProductsByName(search));
    }

    @PostMapping("")
    public ResponseEntity<?> create(@Valid @RequestBody Product body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(body));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        Product product = productService.getById(id).orElseThrow(() -> new ProductNotFoundException(id));
        return ResponseEntity.ok(product);
    }

    @GetMapping("/color")
    public ResponseEntity<?> getByColor(@RequestParam(value = "color", defaultValue = "") String color) {
        List<Product> department = productService.findByColorName(color);
        return ResponseEntity.ok(department);
    }
}


















