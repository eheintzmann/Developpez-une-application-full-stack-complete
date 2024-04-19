package com.openclassrooms.mddapi.exception.token;

import org.springframework.security.core.AuthenticationException;

public class TokenValidationException extends AuthenticationException {

    /**
     * Constructor for TokenValidationException class
     *
     * @param message String
     */
    public TokenValidationException(String message) {
        super(message);
    }

    /**
     * Constructor for TokenValidationException class
     *
     * @param message String
     * @param cause Throwable
     */
    public TokenValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
