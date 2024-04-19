package com.openclassrooms.mddapi.exception.token;

public class TokenGenerationException extends RuntimeException {

    /**
     * Constructor for TokenGenerationException class
     *
     * @param message String
     */
    public TokenGenerationException(String message) {
        super(message);
    }

    /**
     * Constructor for TokenGenerationException class
     *
     * @param message String
     * @param cause Throwable
     */
    public TokenGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
}
