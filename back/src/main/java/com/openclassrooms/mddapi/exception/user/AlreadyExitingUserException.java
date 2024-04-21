package com.openclassrooms.mddapi.exception.user;

public class AlreadyExitingUserException extends RuntimeException {

    /**
     * Constructor for AlreadyExitingUserException class
     *
     * @param message String
     */
    public AlreadyExitingUserException(String message) {
        super(message);
    }

    /**
     * Constructor for AlreadyExitingUserException class
     *
     * @param message String
     * @param cause Throwable
     */
    public AlreadyExitingUserException(String message, Throwable cause) {
        super(message, cause);
    }
}
