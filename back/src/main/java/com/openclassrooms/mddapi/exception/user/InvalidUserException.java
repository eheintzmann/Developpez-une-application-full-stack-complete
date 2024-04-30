package com.openclassrooms.mddapi.exception.user;

import org.springframework.security.core.AuthenticationException;

public class InvalidUserException extends AuthenticationException {

    /**
     * Constructor for InvalidUserException class
     *
     * @param message String
     */
    public InvalidUserException(String message) {
        super(message);
    }

    /**
     * Constructor for InvalidUserException class
     *
     * @param message String
     * @param cause Throwable
     */
    public InvalidUserException(String message, Throwable cause) {
        super(message, cause);
    }
}
