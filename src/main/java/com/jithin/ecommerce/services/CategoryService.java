package com.jithin.ecommerce.services;

import com.jithin.ecommerce.model.Category;
import com.jithin.ecommerce.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends BaseService<CategoryRepository, Category> {
    public CategoryService(CategoryRepository repository) {
        super(repository);
    }


}
