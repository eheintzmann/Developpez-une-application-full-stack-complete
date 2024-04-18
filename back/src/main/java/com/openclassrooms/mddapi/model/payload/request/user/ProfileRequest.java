package com.openclassrooms.mddapi.model.payload.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

/**
 * Profile Request
 */
@Builder
@Data
public class ProfileRequest {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String username;

}
