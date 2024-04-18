package com.openclassrooms.mddapi.exception;


public class CannotAuthenticateException extends RuntimeException {

    /**
     * Constructor for CannotAuthenticateException class
     *
     * @param message String
     */
    public CannotAuthenticateException(String message) {
        super(message);
    }

    /**
     * Constructor for CannotAuthenticateException class
     *
     * @param message String
     * @param cause Throwable
     */
    public CannotAuthenticateException(String message, Throwable cause) {
        super(message, cause);
    }
}
