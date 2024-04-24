package com.openclassrooms.mddapi.exception;

import com.openclassrooms.mddapi.exception.token.TokenGenerationException;
import com.openclassrooms.mddapi.exception.user.AlreadyExitingUserException;
import com.openclassrooms.mddapi.model.payload.response.ApiErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * handler for error 401
     *
     * @param req request
     * @param ex exception
     * @return API error response
     */
    @ExceptionHandler({ AuthenticationException.class })
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ApiErrorResponse> handleAuthenticationExceptions(
            HttpServletRequest req,
            AuthenticationException ex
    ) {
        log.error("Error 401 (Unauthorized) -> {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ApiErrorResponse
                        .builder()
                        .url(req.getRequestURI())
                        .message("Authentication Error")
                        .build()
                );
    }

    /**
     * handler for error 400
     *
     * @param req request
     * @param ex exception
     * @return API error response
     */
    @ExceptionHandler({ AlreadyExitingUserException.class} )
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorResponse> handleAlreadyExistingException(
            HttpServletRequest req,
            AlreadyExitingUserException ex
    ) {
        log.error("Error 400 (Bad Request) -> {}", ex.getMessage());
        return ResponseEntity
                .badRequest()
                .body(ApiErrorResponse
                        .builder()
                        .url(req.getRequestURI())
                        .message("Bad Request")
                        .build()
                );
    }

    /**
     * handler for error 400 (Data Integrity Violation)
     *
     * @param req request
     * @param ex exception
     * @return API error response
     */
    @ExceptionHandler({ DataIntegrityViolationException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorResponse> handleAlreadyExistingException(
            HttpServletRequest req,
            DataIntegrityViolationException ex
    ) {
        log.error("Error 400 (Data Integrity Violation) -> {}", ex.getMessage());
        return ResponseEntity
                .badRequest()
                .body(ApiErrorResponse
                        .builder()
                        .url(req.getRequestURI())
                        .message("Bad Request")
                        .build()
                );
    }


    /**
     * handler for error 500
     *
     * @param req request
     * @param ex exception
     * @return API error response
     */
    @ExceptionHandler({ TokenGenerationException.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ApiErrorResponse> handleTokenGenerationExceptions(
            HttpServletRequest req,
            TokenGenerationException ex
    ) {
        log.error("Error 500 (Token Generation Error) -> {}", ex.getMessage());
        return ResponseEntity
                .internalServerError()
                .body(ApiErrorResponse
                        .builder()
                        .url(req.getRequestURI())
                        .message("Internal Server Error")
                        .build()
                );
    }



    /**
     * handler for generic exceptions
     *
     * @param req request
     * @param ex exception
     * @return API error response
     */
    @ExceptionHandler({ Exception.class })
    public ResponseEntity<ApiErrorResponse> defaultErrorHandler(
            HttpServletRequest req,
            Exception ex
    ) throws Exception {
        // If the exception is annotated with @ResponseStatus rethrow it and let the framework handle it
        log.info("Exception -> {}", ex.getClass().getName());
        if (AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class) != null)
            throw ex;

        // Otherwise setup and send the user to a default error-view.
        log.error("Error 500 (Generic Error) -> {}", ex.getMessage());
        return ResponseEntity
                .internalServerError()
                .body(ApiErrorResponse
                        .builder()
                        .url(req.getRequestURI())
                        .message("Internal Server Error")
                        .build()
                );
    }

}
