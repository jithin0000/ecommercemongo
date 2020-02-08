package com.jithin.ecommerce.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Document
public class Product {

    @Id
    private String id;

    @NotNull(message = "name is required field")
    private String name;

    @NotNull(message="image_url is required field")
    private List<String> image_url = new ArrayList<>();

    @DBRef
    private Category category;

    @NotNull(message = "description is required field")
    private String description;
    @NotNull(message = "price is required field")
    private int price;
    @NotNull(message = "quantity is required field")
    private int quantity;

    private List<ProductColor> colors = new ArrayList<>();
    private List<ProductSizes> sizes = new ArrayList<>();

}
