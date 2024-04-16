package com.openclassrooms.mddapi.exception;

/**
 * Storage error
 */
public class CannotSaveException extends RuntimeException {

    /**
     * Constructor for StorageException class
     *
     * @param message String
     */
    public CannotSaveException(String message) {
        super(message);
    }

    /**
     * Constructor for StorageException class
     *
     * @param message String
     * @param cause Throwable
     */
    public CannotSaveException(String message, Throwable cause) {
        super(message, cause);
    }
}
