package com.openclassrooms.mddapi.model.payload.response.auth;

import com.fasterxml.jackson.annotation.*;
import com.openclassrooms.mddapi.model.payload.response.Response;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.Instant;

/**
 * AuthMeResponse
 */
@Builder
@Data
public class AuthMeResponse implements Response {

    @Positive
    private Long id;

    @NotBlank
    private String name;

    @Email
    private String email;

    @NotNull
    @JsonProperty(value = "created_at")
    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "UTC")

    private Instant createdAt;

    @NotNull
    @JsonProperty(value = "updated_at")
    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "UTC" )

    private Instant updateAt;
}
