package com.openclassrooms.mddapi.exception.token;

import org.springframework.security.core.AuthenticationException;

public class TokenLectureException extends AuthenticationException {

    /**
     * Constructor for TokenLectureException class
     *
     * @param message String
     */
    public TokenLectureException(String message) {
        super(message);
    }

    /**
     * Constructor for TokenLectureException class
     *
     * @param message String
     * @param cause Throwable
     */
    public TokenLectureException(String message, Throwable cause) {
        super(message, cause);
    }
}
