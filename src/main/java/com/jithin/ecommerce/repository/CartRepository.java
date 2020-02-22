package com.jithin.ecommerce.repository;

import com.jithin.ecommerce.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartRepository extends MongoRepository<Cart, String> {

}
