package com.jithin.ecommerce.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Document
public class Role {

    @Id
    private String id;
    @NotNull(message = "name is required field")
    private String name;

    @DBRef(lazy = true)
    private List<User> users;
}
