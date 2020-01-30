package com.jithin.ecommerce.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@Document
public class ProductColor {

    @Id
    private String id;
    @NotNull(message="name is required field")
    private String name;
}
