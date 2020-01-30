package com.jithin.ecommerce.repository;

import com.jithin.ecommerce.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {

}
