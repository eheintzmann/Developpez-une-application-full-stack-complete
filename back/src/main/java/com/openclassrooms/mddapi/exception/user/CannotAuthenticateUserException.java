package com.openclassrooms.mddapi.exception.user;

import org.springframework.security.core.AuthenticationException;

public class CannotAuthenticateUserException extends AuthenticationException {

    /**
     * Constructor for CannotAuthenticateUserException class
     *
     * @param message String
     */
    public CannotAuthenticateUserException(String message) {
        super(message);
    }

    /**
     * Constructor for CannotAuthenticateUserException class
     *
     * @param message String
     * @param cause Throwable
     */
    public CannotAuthenticateUserException(String message, Throwable cause) {
        super(message, cause);
    }
}
