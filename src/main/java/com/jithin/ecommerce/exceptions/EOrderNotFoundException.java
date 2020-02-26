package com.jithin.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EOrderNotFoundException extends RuntimeException {
    public EOrderNotFoundException(String id) {
        super("no order with this id");
    }
}
