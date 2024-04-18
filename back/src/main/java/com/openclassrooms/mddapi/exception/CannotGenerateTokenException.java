package com.openclassrooms.mddapi.exception;

public class CannotGenerateTokenException extends RuntimeException {

    /**
     * Constructor for CannotGenerateTokenException class
     *
     * @param message String
     */
    public CannotGenerateTokenException(String message) {
        super(message);
    }

    /**
     * Constructor for CannotGenerateTokenException class
     *
     * @param message String
     * @param cause Throwable
     */
    public CannotGenerateTokenException(String message, Throwable cause) {
        super(message, cause);
    }
}
