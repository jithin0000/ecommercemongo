package com.jithin.ecommerce.dto;

import com.jithin.ecommerce.model.Role;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class RegisterRequestDto {

    @Email(message = "enter correct email")
    @NotNull(message = "email is an required field")
    private String username;

    @NotNull(message = "password is required field")
    private String password;

    private List<String> roles = new ArrayList<>();
}
