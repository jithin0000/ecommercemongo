package com.jithin.ecommerce.controller;

import com.jithin.ecommerce.exceptions.EOrderNotFoundException;
import com.jithin.ecommerce.model.EOrder;
import com.jithin.ecommerce.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(orderService.getAll());
    }

    @PostMapping("/new")
    public ResponseEntity<?> createOrder(@Valid @RequestBody EOrder order) {
        return new ResponseEntity<>(orderService.create(order), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEOrderById(@PathVariable String id) {
        EOrder department = orderService.getById(id).orElseThrow(() -> new EOrderNotFoundException(id));
        return ResponseEntity.ok(department);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {

        String message = orderService.delete(id);
        return ResponseEntity.ok(message);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateById(@PathVariable String id, @Valid @RequestBody EOrder body) {

        return ResponseEntity.ok(orderService.updateById(id, body));
    }
}

















