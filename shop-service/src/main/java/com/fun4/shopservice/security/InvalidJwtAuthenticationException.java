package com.fun4.productservice.security;

import javax.security.sasl.AuthenticationException;

public class InvalidJwtAuthenticationException extends AuthenticationException {
    public InvalidJwtAuthenticationException(String e) {
        super(e);
    }
}
