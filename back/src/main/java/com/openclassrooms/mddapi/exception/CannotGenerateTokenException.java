package com.openclassrooms.mddapi.exception;

/**
 * Storage error
 */
public class CannotGenerateTokenException extends RuntimeException {

    /**
     * Constructor for StorageException class
     *
     * @param message String
     */
    public CannotGenerateTokenException(String message) {
        super(message);
    }

    /**
     * Constructor for StorageException class
     *
     * @param message String
     * @param cause Throwable
     */
    public CannotGenerateTokenException(String message, Throwable cause) {
        super(message, cause);
    }
}
