package com.openclassrooms.mddapi.exception;

public class NotExistingTopicException extends RuntimeException {

    /**
     * Constructor for NotExistingTopicException class
     *
     * @param message String
     */
    public NotExistingTopicException(String message) {
        super(message);
    }

    /**
     * Constructor for NotExistingTopicException class
     *
     * @param message String
     * @param cause Throwable
     */
    public NotExistingTopicException(String message, Throwable cause) {
        super(message, cause);
    }
}
