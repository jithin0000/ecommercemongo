package com.jithin.ecommerce.services;

import com.jithin.ecommerce.exceptions.CartNotFoundException;
import com.jithin.ecommerce.model.Cart;
import com.jithin.ecommerce.repository.CartRepository;
import org.springframework.stereotype.Service;

@Service
public class CartService extends BaseService<CartRepository, Cart>{
    public CartService(CartRepository repository) {
        super(repository);
    }

    public Cart update(String id, Cart body) {
        Cart cart = getRepository().findById(id).orElseThrow(() -> new CartNotFoundException(id));
        cart.setProducts(body.getProducts());
        cart.setUser(body.getUser());
        return getRepository().save(cart);
    }
}
