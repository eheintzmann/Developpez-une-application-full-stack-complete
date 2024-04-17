package com.openclassrooms.mddapi.exception;

/**
 * Storage error
 */
public class InvalidJWTSubjectException extends RuntimeException {

    /**
     * Constructor for StorageException class
     *
     * @param message String
     */
    public InvalidJWTSubjectException(String message) {
        super(message);
    }

    /**
     * Constructor for StorageException class
     *
     * @param message String
     * @param cause Throwable
     */
    public InvalidJWTSubjectException(String message, Throwable cause) {
        super(message, cause);
    }
}
