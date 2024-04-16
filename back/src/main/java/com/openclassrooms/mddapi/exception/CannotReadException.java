package com.openclassrooms.mddapi.exception;

/**
 * Storage error
 */
public class CannotReadException extends RuntimeException {

    /**
     * Constructor for StorageException class
     *
     * @param message String
     */
    public CannotReadException(String message) {
        super(message);
    }

    /**
     * Constructor for StorageException class
     *
     * @param message String
     * @param cause Throwable
     */
    public CannotReadException(String message, Throwable cause) {
        super(message, cause);
    }
}
