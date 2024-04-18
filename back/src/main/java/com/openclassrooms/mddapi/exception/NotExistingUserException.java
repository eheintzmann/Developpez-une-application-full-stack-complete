package com.openclassrooms.mddapi.exception;

public class NotExistingUserException extends RuntimeException {

    /**
     * Constructor for NotExistingUserException class
     *
     * @param message String
     */
    public NotExistingUserException(String message) {
        super(message);
    }

    /**
     * Constructor for NotExistingUserException class
     *
     * @param message String
     * @param cause Throwable
     */
    public NotExistingUserException(String message, Throwable cause) {
        super(message, cause);
    }
}
