package com.openclassrooms.mddapi.exception.user;

public class NotUniqueUsernameException extends RuntimeException {

    /**
     * Constructor for NotUniqueUsernameException class
     *
     * @param message String
     */
    public NotUniqueUsernameException(String message) {
        super(message);
    }

    /**
     * Constructor for NotUniqueUsernameException class
     *
     * @param message String
     * @param cause Throwable
     */
    public NotUniqueUsernameException(String message, Throwable cause) {
        super(message, cause);
    }
}
