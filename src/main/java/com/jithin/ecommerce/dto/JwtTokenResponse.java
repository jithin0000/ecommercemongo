package com.jithin.ecommerce.dto;

import lombok.Data;

@Data
public class JwtTokenResponse {

    private String token;

    public JwtTokenResponse(String jwtToken) {
        this.token = jwtToken;
    }
}
