package com.openclassrooms.mddapi.model.payload.request.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

/**
 * Login Request
 */
@Getter
public class LoginRequest {

    @NotBlank(message = "email or username is required")
    @Size(max = 255, message = "255 characters maximum")
    private String login;

    @NotBlank(message = "password is required")
    @Size(max = 255, message = "255 characters maximum")
    private String password;
}
