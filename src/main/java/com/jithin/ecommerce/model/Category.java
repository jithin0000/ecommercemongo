package com.jithin.ecommerce.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;

@Data
@Document
public class Category {

    @Id
    private String id;

    @NotNull(message = "name is required field")
    @Indexed(unique = true)
    private String name;


}
