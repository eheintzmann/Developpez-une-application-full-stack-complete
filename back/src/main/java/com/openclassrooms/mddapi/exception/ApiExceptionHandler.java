package com.openclassrooms.mddapi.exception;

import com.openclassrooms.mddapi.exception.post.NonExistingPostException;
import com.openclassrooms.mddapi.exception.token.TokenGenerationException;
import com.openclassrooms.mddapi.exception.user.AlreadyExitingUserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.*;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;


@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {


    /**
     * handler for error 400
     *
     * @param req request
     * @param ex  exception
     * @return Problem details
     */
    @ExceptionHandler({AlreadyExitingUserException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ProblemDetail> handleAlreadyExistingExceptions(
            AlreadyExitingUserException ex,
            WebRequest req
    ) {
        logError(HttpStatus.BAD_REQUEST, ex);
        return ResponseEntity
                .badRequest()
                .body(
                        ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage())
                );
    }


    /**
     * handler for error 400
     *
     * @param ex MethodArgumentNotValidException
     * @param headers HttpHeaders
     * @param status HttpStatus
     * @param request WebRequest
     * @return Problem details
     */
    @Override
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {

        logError(HttpStatus.valueOf(ex.getStatusCode().value()), ex);


        Map<String, String> errors = new LinkedHashMap<>();
        ex.getBindingResult().getAllErrors().forEach(objectError -> {
            String fieldName = ((FieldError) objectError).getField();
            String message = objectError.getDefaultMessage();
            errors.put(fieldName, message);
        });
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                "validation failed"
        );
        problemDetail.setProperty("errors", errors);
        return ResponseEntity
                .badRequest()
                .body(problemDetail);
    }


    /**
     * handler for error 401
     *
     * @param req request
     * @param ex  exception
     * @return Problem details
     */
    @ExceptionHandler({AuthenticationException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ProblemDetail> handleAuthenticationExceptions(
            AuthenticationException ex,
            WebRequest req
    ) {
        logError(HttpStatus.UNAUTHORIZED, ex);
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(
                        ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, ex.getMessage())
                );
    }


    /**
     * handler for error 403
     *
     * @param req request
     * @param ex  exception
     * @return Problem details
     */
    @ExceptionHandler({AccessDeniedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ProblemDetail> handleAccessDeniedExceptions(
            AccessDeniedException ex,
            WebRequest req
    ) {
        logError(HttpStatus.FORBIDDEN, ex);
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(
                        ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, ex.getMessage())
                );
    }


    /**
     * handler for error 404
     *
     * @param req request
     * @param ex  exception
     * @return Problem details
     */
    @ExceptionHandler({NonExistingPostException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ProblemDetail> handleNonExistingPostExceptions(
            NonExistingPostException ex,
            WebRequest req
    ) {
        logError(HttpStatus.NOT_FOUND, ex);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage())
                );
    }


    /**
     * handler for error 500
     *
     * @param req request
     * @param ex  exception
     * @return Problem details
     */
    @ExceptionHandler({TokenGenerationException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ProblemDetail> handleTokenGenerationExceptions(
            TokenGenerationException ex,
            WebRequest req
    ) {
        logError(HttpStatus.INTERNAL_SERVER_ERROR, ex);
        return ResponseEntity
                .internalServerError()
                .body(
                        ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage())
                );
    }


    /**
     * handler for generic exceptions
     *
     * @param req request
     * @param ex  exception
     * @return Problem details
     */
    @ExceptionHandler({Exception.class})
    public ResponseEntity<ProblemDetail> defaultErrorHandler(
            Exception ex,
            WebRequest req
    ) {

        log.info("Exception -> {}", ex.getClass().getName());

        logError(HttpStatus.INTERNAL_SERVER_ERROR, ex);
        return ResponseEntity
                .internalServerError()
                .body(
                        ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "an error occurred")
                );
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