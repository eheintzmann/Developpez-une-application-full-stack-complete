package com.openclassrooms.mddapi.exception;

public class CannotReadException extends RuntimeException {

    /**
     * Constructor for CannotReadException class
     *
     * @param message String
     */
    public CannotReadException(String message) {
        super(message);
    }

    /**
     * Constructor for CannotReadException class
     *
     * @param message String
     * @param cause Throwable
     */
    public CannotReadException(String message, Throwable cause) {
        super(message, cause);
    }
}
