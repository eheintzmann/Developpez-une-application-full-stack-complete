package com.openclassrooms.mddapi.exception;

public class InvalidJWTSubjectException extends RuntimeException {

    /**
     * Constructor for InvalidJWTSubjectException class
     *
     * @param message String
     */
    public InvalidJWTSubjectException(String message) {
        super(message);
    }

    /**
     * Constructor for InvalidJWTSubjectException class
     *
     * @param message String
     * @param cause Throwable
     */
    public InvalidJWTSubjectException(String message, Throwable cause) {
        super(message, cause);
    }
}
