package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.model.payload.response.EmptyResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public EmptyResponse handleValidationExceptions() {
        log.error("Error 400 - Bad Request");
        return new EmptyResponse();
    }

}
