package com.jithin.ecommerce.controller;

import com.jithin.ecommerce.exceptions.CartNotFoundException;
import com.jithin.ecommerce.model.Cart;
import com.jithin.ecommerce.model.Product;
import com.jithin.ecommerce.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/new")
    public ResponseEntity<?> createCart(@Valid @RequestBody Cart body) {
        return new ResponseEntity<>(cartService.create(body), HttpStatus.CREATED);
    }

    @PostMapping("/add/{id}")
    public ResponseEntity<?> addToCart(@PathVariable String id, @RequestBody Product product) {
        Cart cart = cartService.getById(id).orElseThrow(() -> new CartNotFoundException(id));

        if (cart.getProducts().isEmpty()) {
            cart.addToCart(product);

        }else{
            for (int i = 0; i < cart.getProducts().size(); i++) {
                if (cart.getProducts().contains(product)) {
                    cart.removeCart(product);
                }else{
                    cart.addToCart(product);
                }
            }
        }

        int total = 0;
        for (int i = 0; i < cart.getProducts().size(); i++) {
             total = cart.getCartTotal();
        }

        cart.setCartTotal(total);

        return new ResponseEntity<>(cartService.create(cart), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCart(@PathVariable String id) {
        Cart cart = cartService.getById(id).orElseThrow(() -> new CartNotFoundException(id));
        return ResponseEntity.ok(cart);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCart(@PathVariable String id, @Valid @RequestBody Cart body){
        return ResponseEntity.ok(cartService.update(id, body));
    }
}
