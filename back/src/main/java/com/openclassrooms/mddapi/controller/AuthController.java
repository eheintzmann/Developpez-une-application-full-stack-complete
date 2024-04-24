package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.model.dto.UserDTO;
import com.openclassrooms.mddapi.model.entity.User;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

        String token = this.authService.registerUser(
                request.getEmail(),
                request.getUsername(),
                request.getPassword()
        );

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

        String token = this.authService
                .loginUser(
                        request.getEmail(),
                        request.getPassword()
                );

        return ResponseEntity.ok(
                new TokenResponse(token)
        );
    }

    /**
     * /auth/me route
     *
     * @param user User
     * @return ResponseEntity
     */
    @GetMapping(
            path = "me",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AuthMeResponse> autMe(@AuthenticationPrincipal User user) {

        UserDTO userDTO = authService.authUser(user);

        return ResponseEntity.ok(conversionService.convert(userDTO, AuthMeResponse.class));
    }

}
