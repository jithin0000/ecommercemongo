package com.jithin.ecommerce.repository;

import com.jithin.ecommerce.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CategoryRepository extends MongoRepository<Category, String> {

    @Query(value = "{ 'name': { $regex : ?0, $options : 'i' } }")
    Page<Category> findByNameRegex(String name, Pageable pageable);

}
