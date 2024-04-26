package com.openclassrooms.mddapi.exception;

import com.openclassrooms.mddapi.exception.post.NonExistingPostException;
import com.openclassrooms.mddapi.exception.token.TokenGenerationException;
import com.openclassrooms.mddapi.exception.user.AlreadyExitingUserException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.LinkedHashMap;
import java.util.Map;


@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class ApiExceptionHandler {


    /**
     * handler for error 400
     *
     * @param req request
     * @param ex  exception
     * @return API error response
     */
    @ExceptionHandler({AlreadyExitingUserException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<LinkedHashMap<String, Object>> handleAlreadyExistingException(
            HttpServletRequest req,
            AlreadyExitingUserException ex
    ) {
        logError(HttpStatus.BAD_REQUEST, ex);
        return ResponseEntity
                .badRequest()
                .body(
                        getResponseBody("", req, HttpStatus.BAD_REQUEST)
                );
    }

    /**
     * handler for error 400 (HttpMessageNotReadableException)
     *
     * @param req request
     * @param ex  exception
     * @return API error response
     */
    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<LinkedHashMap<String, Object>> handleAlreadyExistingException(
            HttpServletRequest req,
            HttpMessageNotReadableException ex
    ) {
        logError(HttpStatus.BAD_REQUEST, ex);
        return ResponseEntity
                .badRequest()
                .body(
                        getResponseBody("", req, HttpStatus.BAD_REQUEST)
                );
    }


    /**
     * handler for error 400 (Data Integrity Violation)
     *
     * @param req request
     * @param ex  exception
     * @return API error response
     */
    @ExceptionHandler({DataIntegrityViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<LinkedHashMap<String, Object>> handleAlreadyExistingException(
            HttpServletRequest req,
            Exception ex
    ) {
        logError(HttpStatus.BAD_REQUEST, ex);
        return ResponseEntity
                .badRequest()
                .body(
                        getResponseBody("", req, HttpStatus.BAD_REQUEST)
                );
    }


    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<LinkedHashMap<String, Object>> handleAlreadyExistingException(
            HttpServletRequest req,
            MethodArgumentNotValidException ex
    ) {
        logError(HttpStatus.valueOf(ex.getStatusCode().value()), ex);


        Map<String, String> errors = new LinkedHashMap<>();
        ex.getBindingResult().getAllErrors().forEach(objectError -> {
            String fieldName = ((FieldError) objectError).getField();
            String message = objectError.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return ResponseEntity
                .badRequest()
                .body(
                        getResponseBody(errors, req, HttpStatus.BAD_REQUEST)
                );
    }


    /**
     * handler for error 401
     *
     * @param req request
     * @param ex  exception
     * @return API error response
     */
    @ExceptionHandler({AuthenticationException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<LinkedHashMap<String, Object>> handleAuthenticationExceptions(
            HttpServletRequest req,
            AuthenticationException ex
    ) {
        logError(HttpStatus.UNAUTHORIZED, ex);
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(
                        getResponseBody("Authentication Error", req, HttpStatus.UNAUTHORIZED)
                );
    }

    /**
     * handler for error 403
     *
     * @param req request
     * @param ex  exception
     * @return API error response
     */
    @ExceptionHandler({AccessDeniedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<LinkedHashMap<String, Object>> handleAccessDeniedExceptions(
            HttpServletRequest req,
            AccessDeniedException ex
    ) {
        logError(HttpStatus.FORBIDDEN, ex);
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(getResponseBody("", req, HttpStatus.FORBIDDEN));
    }

    /**
     * handler for error 404
     *
     * @param req request
     * @param ex  exception
     * @return API error response
     */
    @ExceptionHandler({NonExistingPostException.class, NoResourceFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<LinkedHashMap<String, Object>> handleNonExistingPostException(
            HttpServletRequest req,
            Exception ex
    ) {
        logError(HttpStatus.NOT_FOUND, ex);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(getResponseBody("", req, HttpStatus.NOT_FOUND));
    }


    /**
     * handler for error 500
     *
     * @param req request
     * @param ex  exception
     * @return API error response
     */
    @ExceptionHandler({TokenGenerationException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<LinkedHashMap<String, Object>> handleTokenGenerationExceptions(
            HttpServletRequest req,
            TokenGenerationException ex
    ) {
        logError(HttpStatus.INTERNAL_SERVER_ERROR, ex);
        return ResponseEntity
                .internalServerError()
                .body(
                        getResponseBody("", req, HttpStatus.INTERNAL_SERVER_ERROR)
                );
    }


    /**
     * handler for generic exceptions
     *
     * @param req request
     * @param ex  exception
     * @return API error response
     */
    @ExceptionHandler({Exception.class})
    public ResponseEntity<LinkedHashMap<String, Object>> defaultErrorHandler(
            HttpServletRequest req,
            Exception ex
    ) {

        log.info("Exception -> {}", ex.getClass().getName());

        logError(HttpStatus.BAD_REQUEST, ex);
        return ResponseEntity
                .internalServerError()
                .body(
                        getResponseBody("", req, HttpStatus.INTERNAL_SERVER_ERROR)
                );
    }

    /**
     *
     * @param messages Object
     * @param req HttpServletRequest
     * @param status HttpStatus
     * @return LinkedHashMap<String,Object>
     */
    private LinkedHashMap<String, Object> getResponseBody(
            Object messages,
            HttpServletRequest req,
            HttpStatus status
    ) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("error", status.getReasonPhrase());
        map.put("status", Integer.toString(status.value()));
        map.put("uri", req.getRequestURI());
        if (messages instanceof String message && message.isBlank()) {
            return map;
        }
        map.put("message", messages);
        return map;
    }

    /**
     *
     * @param status HttpStatus
     * @param ex Exception
     */
    private void logError(HttpStatus status, Exception ex) {
        log.error("Error {} ({}) -> {}", status, ex.getClass().getSimpleName(), ex.getMessage());
    }
}