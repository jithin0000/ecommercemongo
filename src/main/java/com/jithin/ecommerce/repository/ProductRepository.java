package com.jithin.ecommerce.repository;

import com.jithin.ecommerce.model.Category;
import com.jithin.ecommerce.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    @Query(value = "{ 'name': { $regex : ?0, $options : 'i' } }")
    List<Product> findByNameRegex(String name);
}
