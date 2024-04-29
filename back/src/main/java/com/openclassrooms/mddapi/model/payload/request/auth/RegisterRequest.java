package com.openclassrooms.mddapi.model.payload.request.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

/**
 * Register Request
 */
@Getter
public class RegisterRequest {

    @Email(message = "invalid email")
    @Size(max = 255, message = "255 characters maximum")
    @NotBlank(message = "email is required")
    private String email;

    @NotBlank(message = "username is required")
    @Size(max = 255, message = "255 characters maximum")
    @Pattern(regexp = "^[a-zA-Z0-9_-]{3,}$", message = "invalid username")
    private String username;

    @NotBlank(message = "password is required")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\\W)(?!.* ).{8,}$", message = "invalid password")
    @Size(max = 255, message = "255 characters maximum")
    private String password;
}
