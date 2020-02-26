package com.jithin.ecommerce.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
@Data
@Document
public class Cart {
    @Id
    private String id;

    @DBRef
    private User user;

    private int cartTotal =0;
    private List<Product> products = new ArrayList<>();

    public void addToCart(Product product) {
        this.products.add(product);
    }

    public void removeCart(Product product) {
        this.products.remove(product);
    }
}
