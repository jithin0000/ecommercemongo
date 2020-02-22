package com.jithin.ecommerce.services;

import com.jithin.ecommerce.model.Product;
import com.jithin.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductService extends BaseService<ProductRepository, Product> {
    public ProductService(ProductRepository repository) {
        super(repository);
    }

    public List<Product> getProductsByName(String name) {
        if (StringUtils.isEmpty(name)) {
            return this.getRepository().findAll();
        }
        return this.getRepository().findByNameRegex(name);
    }

    public List<Product> findByColorName(String color) {
        return getRepository().findByColors_Name(color);
    }
}
