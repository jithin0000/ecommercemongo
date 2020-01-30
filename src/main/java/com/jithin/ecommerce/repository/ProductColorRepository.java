package com.jithin.ecommerce.repository;

import com.jithin.ecommerce.model.ProductColor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductColorRepository extends MongoRepository<ProductColor, String> {
}
