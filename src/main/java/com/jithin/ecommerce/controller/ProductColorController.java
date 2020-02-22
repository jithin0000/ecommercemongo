package com.jithin.ecommerce.controller;

import com.jithin.ecommerce.model.ProductColor;
import com.jithin.ecommerce.services.ProductColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/color")
public class ProductColorController {

    @Autowired
    private ProductColorService colorService;

    @GetMapping("")
    public ResponseEntity<?> getAllColors() {
        return ResponseEntity.ok(colorService.getAll());
    }

    @PostMapping("")
    public ResponseEntity<ProductColor> createColor(@Valid @RequestBody ProductColor color) {
        return new ResponseEntity<>(colorService.create(color), HttpStatus.CREATED);
    }


}
