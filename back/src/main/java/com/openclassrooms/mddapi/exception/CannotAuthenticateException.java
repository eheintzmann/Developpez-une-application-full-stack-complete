package com.openclassrooms.mddapi.exception;

/**
 * Storage error
 */
public class CannotAuthenticateException extends RuntimeException {

    /**
     * Constructor for StorageException class
     *
     * @param message String
     */
    public CannotAuthenticateException(String message) {
        super(message);
    }

    /**
     * Constructor for StorageException class
     *
     * @param message String
     * @param cause Throwable
     */
    public CannotAuthenticateException(String message, Throwable cause) {
        super(message, cause);
    }
}
