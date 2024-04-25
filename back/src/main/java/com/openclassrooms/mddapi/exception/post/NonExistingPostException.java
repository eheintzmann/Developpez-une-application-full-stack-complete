package com.openclassrooms.mddapi.exception.post;

public class NonExistingPostException extends RuntimeException {

    /**
     * Constructor for NonExistingPostException class
     *
     * @param message String
     */
    public NonExistingPostException(String message) {
        super(message);
    }

    /**
     * Constructor for NonExistingPostException class
     *
     * @param message String
     * @param cause Throwable
     */
    public NonExistingPostException(String message, Throwable cause) {
        super(message, cause);
    }
}
