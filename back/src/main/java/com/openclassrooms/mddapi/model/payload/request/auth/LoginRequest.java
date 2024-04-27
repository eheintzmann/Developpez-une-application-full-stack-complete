package com.openclassrooms.mddapi.model.payload.request.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

/**
 * Login Request
 */
@Getter
public class LoginRequest {

    @Email(message = "invalid email")
    @NotBlank(message = "email is required")
    private String email;

    @NotBlank(message = "password is required")
    private String password;
}
