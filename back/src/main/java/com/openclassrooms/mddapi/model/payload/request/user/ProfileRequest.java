package com.openclassrooms.mddapi.model.payload.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

/**
 * Profile Request
 */
@Builder
@Data
public class ProfileRequest {

    @Email(message = "invalid email")
    private String email;

    private String username;

    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\\W)(?!.* ).{8,}$", message = "invalid password")
    private String password;
}
