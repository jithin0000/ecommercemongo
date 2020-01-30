package com.jithin.ecommerce.services;

import com.jithin.ecommerce.model.Product;
import com.jithin.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends BaseService<ProductRepository, Product> {
    public ProductService(ProductRepository repository) {
        super(repository);
    }

}
