package com.openclassrooms.mddapi.exception;

/**
 * Storage error
 */
public class UserAlreadyExitsException extends RuntimeException {

    /**
     * Constructor for StorageException class
     *
     * @param message String
     */
    public UserAlreadyExitsException(String message) {
        super(message);
    }

    /**
     * Constructor for StorageException class
     *
     * @param message String
     * @param cause Throwable
     */
    public UserAlreadyExitsException(String message, Throwable cause) {
        super(message, cause);
    }
}
