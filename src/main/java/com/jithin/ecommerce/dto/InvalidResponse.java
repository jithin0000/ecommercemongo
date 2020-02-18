package com.jithin.ecommerce.dto;

import lombok.Data;

@Data
public class InvalidResponse {

    private String username;
    private String password;

    public InvalidResponse() {
        this.username = "invalid username";
        this.password = "invalid password";
    }
}
