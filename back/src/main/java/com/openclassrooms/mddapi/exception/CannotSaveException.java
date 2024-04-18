package com.openclassrooms.mddapi.exception;

public class CannotSaveException extends RuntimeException {

    /**
     * Constructor for CannotSaveException class
     *
     * @param message String
     */
    public CannotSaveException(String message) {
        super(message);
    }

    /**
     * Constructor for CannotSaveException class
     *
     * @param message String
     * @param cause Throwable
     */
    public CannotSaveException(String message, Throwable cause) {
        super(message, cause);
    }
}
