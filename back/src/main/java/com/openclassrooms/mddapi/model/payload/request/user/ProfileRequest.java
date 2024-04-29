package com.openclassrooms.mddapi.model.payload.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

/**
 * Profile Request
 */
@Getter
public class ProfileRequest {

    @Email(message = "invalid email")
    @Size(max = 255, message = "255 characters maximum")
    private String email;

    @Pattern(regexp = "^[a-zA-Z0-9_-]{3,}$", message = "invalid username")
    @Size(max = 255, message = "255 characters maximum")
    private String username;

    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\\W)(?!.* ).{8,}$", message = "invalid password")
    @Size(max = 255, message = "255 characters maximum")
    private String password;
}
