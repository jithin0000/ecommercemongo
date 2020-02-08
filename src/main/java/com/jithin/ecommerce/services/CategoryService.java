package com.jithin.ecommerce.services;

import com.jithin.ecommerce.model.Category;
import com.jithin.ecommerce.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CategoryService extends BaseService<CategoryRepository, Category> {
    public CategoryService(CategoryRepository repository) {
        super(repository);
    }


    public Page<Category> getPaginatedFilterCategories( int page, int size , String sort, String search) {
        Pageable pageRequest = PageRequest.of(page,size, Sort.by(sort));

        Page<Category> categories;
        if (StringUtils.hasText(search)) {
            categories = getRepository().findByNameRegex(search, pageRequest);
        }else{
            categories = getRepository().findAll(pageRequest);
        }

        return categories;
    }

}
