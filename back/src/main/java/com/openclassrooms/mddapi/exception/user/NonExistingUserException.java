package com.openclassrooms.mddapi.exception.user;

import org.springframework.security.core.AuthenticationException;

public class NonExistingUserException extends AuthenticationException {

    /**
     * Constructor for NonExistingUserException class
     *
     * @param message String
     */
    public NonExistingUserException(String message) {
        super(message);
    }

    /**
     * Constructor for NonExistingUserException class
     *
     * @param message String
     * @param cause Throwable
     */
    public NonExistingUserException(String message, Throwable cause) {
        super(message, cause);
    }
}
