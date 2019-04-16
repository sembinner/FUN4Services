package com.fun4.shopservice.security;

import javax.security.sasl.AuthenticationException;

public class InvalidJwtAuthenticationException extends AuthenticationException {
    public InvalidJwtAuthenticationException(String e) {
        super(e);
    }
}
