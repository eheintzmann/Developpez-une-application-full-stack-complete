package com.openclassrooms.mddapi.exception;

import org.springframework.security.core.AuthenticationException;

public class CannotGenerateTokenException extends AuthenticationException {

    /**
     * Constructor for CannotGenerateTokenException class
     *
     * @param message String
     */
    public CannotGenerateTokenException(String message) {
        super(message);
    }

    /**
     * Constructor for CannotGenerateTokenException class
     *
     * @param message String
     * @param cause Throwable
     */
    public CannotGenerateTokenException(String message, Throwable cause) {
        super(message, cause);
    }
}
