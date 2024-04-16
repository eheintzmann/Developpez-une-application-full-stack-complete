package com.openclassrooms.mddapi.exception;

/**
 * Storage error
 */
public class NotExistingUserException extends RuntimeException {

    /**
     * Constructor for StorageException class
     *
     * @param message String
     */
    public NotExistingUserException(String message) {
        super(message);
    }

    /**
     * Constructor for StorageException class
     *
     * @param message String
     * @param cause Throwable
     */
    public NotExistingUserException(String message, Throwable cause) {
        super(message, cause);
    }
}
