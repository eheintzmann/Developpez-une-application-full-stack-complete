package com.openclassrooms.mddapi.exception.topic;

public class NonExistingTopicException extends RuntimeException {

    /**
     * Constructor for NonExistingTopicException class
     *
     * @param message String
     */
    public NonExistingTopicException(String message) {
        super(message);
    }

    /**
     * Constructor for NonExistingTopicException class
     *
     * @param message String
     * @param cause Throwable
     */
    public NonExistingTopicException(String message, Throwable cause) {
        super(message, cause);
    }
}
