package com.jithin.ecommerce.services;

import com.jithin.ecommerce.model.ProductColor;
import com.jithin.ecommerce.repository.ProductColorRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductColorService extends BaseService<ProductColorRepository, ProductColor> {
    public ProductColorService(ProductColorRepository repository) {
        super(repository);
    }
}
