package com.jithin.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserRoleNotFoundException extends RuntimeException {
    public UserRoleNotFoundException(String no_role_with_this_name) {
        super(no_role_with_this_name);
    }
}
