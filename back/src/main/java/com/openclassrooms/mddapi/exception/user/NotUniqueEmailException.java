package com.openclassrooms.mddapi.exception.user;

public class NotUniqueEmailException extends RuntimeException {

    /**
     * Constructor for NotUniqueEmailException class
     *
     * @param message String
     */
    public NotUniqueEmailException(String message) {
        super(message);
    }

    /**
     * Constructor for NotUniqueEmailException class
     *
     * @param message String
     * @param cause Throwable
     */
    public NotUniqueEmailException(String message, Throwable cause) {
        super(message, cause);
    }
}
