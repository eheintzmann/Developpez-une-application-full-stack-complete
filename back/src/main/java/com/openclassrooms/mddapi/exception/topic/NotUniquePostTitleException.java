package com.openclassrooms.mddapi.exception.topic;

public class NotUniquePostTitleException extends RuntimeException {

    /**
     * Constructor for NotUniquePostTitleException class
     *
     * @param message String
     */
    public NotUniquePostTitleException(String message) {
        super(message);
    }

    /**
     * Constructor for NotUniquePostTitleException class
     *
     * @param message String
     * @param cause Throwable
     */
    public NotUniquePostTitleException(String message, Throwable cause) {
        super(message, cause);
    }
}
