package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.exception.*;
import com.openclassrooms.mddapi.model.payload.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * Global Controller for Exception handling
 */
@Slf4j
@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    /**
     * handler for error 400
     *
     * @return Response
     */
    @ExceptionHandler(value = {
            MethodArgumentNotValidException.class,
            UserAlreadyExitsException.class,
            SQLIntegrityConstraintViolationException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Response> handleBadRequestExceptions(Exception ex) {
        log.error("Error 400 (Bad Request) - {}", ex.getMessage());
        return ResponseEntity
                .badRequest()
                .build();
    }

    /**
     * handler for error 401
     *
     * @return Response
     */
    @ExceptionHandler(value = {
            NotExistingUserException.class,
            InvalidJWTSubjectException.class,
            CannotAuthenticateException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Response> handleUnauthorizedExceptions(Exception ex) {
        log.error("Error 401 (Unauthorized) - {} ", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .build();
    }

    /**
     * handler for error 500
     * @return Response
     */
    @ExceptionHandler(value = {
            CannotReadException.class,
            CannotSaveException.class,
            CannotGenerateTokenException.class,
    })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Response> handleInternalErrorExceptions(Exception ex) {
        log.error("Error 500 (Internal Server Error) - {}", ex.getMessage());
        return ResponseEntity
                .internalServerError()
                .build();
    }

}
