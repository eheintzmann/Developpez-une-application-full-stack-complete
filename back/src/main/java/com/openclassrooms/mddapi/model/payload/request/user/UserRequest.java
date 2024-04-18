package com.openclassrooms.mddapi.model.payload.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

/**
 * User Request
 */
@Builder
@Data
public class UserRequest {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String username;

}
