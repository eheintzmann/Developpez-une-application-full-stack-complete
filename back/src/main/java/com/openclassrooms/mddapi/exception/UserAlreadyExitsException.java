package com.openclassrooms.mddapi.exception;

public class UserAlreadyExitsException extends RuntimeException {

    /**
     * Constructor for UserAlreadyExitsException class
     *
     * @param message String
     */
    public UserAlreadyExitsException(String message) {
        super(message);
    }

    /**
     * Constructor for UserAlreadyExitsException class
     *
     * @param message String
     * @param cause Throwable
     */
    public UserAlreadyExitsException(String message, Throwable cause) {
        super(message, cause);
    }
}
