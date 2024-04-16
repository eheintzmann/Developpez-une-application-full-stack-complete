package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.exception.CannotAuthenticateException;
import com.openclassrooms.mddapi.exception.NotExistingUserException;
import com.openclassrooms.mddapi.exception.UserAlreadyExitsException;
import com.openclassrooms.mddapi.model.dto.UserDTO;
import com.openclassrooms.mddapi.model.payload.request.auth.LoginRequest;
import com.openclassrooms.mddapi.model.payload.response.auth.TokenResponse;
import com.openclassrooms.mddapi.model.payload.request.auth.RegisterRequest;
import com.openclassrooms.mddapi.model.payload.response.auth.AuthMeResponse;
import com.openclassrooms.mddapi.service.IAuthService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * Authentification REST controller
 */
@Slf4j
@RestController
@RequestMapping("/api/auth/")
public class AuthController {
    private final IAuthService authService;
    private final ConversionService conversionService;

    /**
     * Constructor for AuthentificationController class
     *
     * @param authService AuthentificationService
     * @param conversionService ConversionService
     */
    public AuthController(
            IAuthService authService,
            ConversionService conversionService
    ) {
        this.authService = authService;
        this.conversionService = conversionService;
    }

    /**
     * Register route
     *
     * @param request RegisterRequest
     * @return ResponseEntity
     */
    @PostMapping(
            path = "register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<TokenResponse> register(@Valid @RequestBody RegisterRequest request) {

        String token;
        try {
            token = this.authService.registerUser(
                    request.getEmail(),
                    request.getName(),
                    request.getPassword()
            );
        } catch (UserAlreadyExitsException ex) {
            log.error("Email {} already used", request.getEmail());
            return ResponseEntity
                    .badRequest()
                    .build();
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity
                    .internalServerError()
                    .build();
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new TokenResponse(token));
    }

    /**
     * Login route
     *
     * @param request LoginRequest
     * @return ResponseEntity
     */
    @PostMapping(
            path = "login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )

    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest request) {

        String token;
        try {
            token = this.authService.loginUser(
                    request.getEmail(),
                    request.getPassword()
            );
        } catch (CannotAuthenticateException ex) {
            log.info("Cannot authenticate user {}", request.getEmail());
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .build();
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity
                    .internalServerError()
                    .build();
        }

        return ResponseEntity.ok(new TokenResponse(token));
    }

    /**
     * /auth/me route
     *
     * @param principal Principal
     * @return ResponseEntity
     */
    @GetMapping(
            path = "me",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AuthMeResponse> autMe(Principal principal) {

        if (principal == null) {
            log.error("Principal is null.");
            return ResponseEntity
                    .internalServerError()
                    .build();
        }

        UserDTO userDTO;
        try {
            userDTO = authService.authUser(principal.getName());
        } catch (NotExistingUserException ex) {
            log.info("User {} does not exist.", principal.getName());
            return ResponseEntity
                    .notFound()
                    .build();
        } catch (Exception ex) {
            log.error("Internal error.");
            return ResponseEntity
                    .internalServerError()
                    .build();
        }

        AuthMeResponse authMeResponse;
        try {
            authMeResponse = conversionService.convert(userDTO, AuthMeResponse.class);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity
                    .internalServerError()
                    .build();
        }
        return ResponseEntity.ok(authMeResponse);
    }

}
