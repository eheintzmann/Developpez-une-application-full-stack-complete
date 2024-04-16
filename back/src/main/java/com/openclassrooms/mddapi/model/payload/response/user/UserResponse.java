package com.openclassrooms.mddapi.model.payload.response.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.openclassrooms.mddapi.model.payload.response.Response;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

/**
 * UserResponse DTO
 */
@Data
@Builder
public class UserResponse implements Response {

    private int id;

    private String email;

    private String name;

    @JsonProperty(value = "created_at")
    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "UTC")
    private Instant createdAt;

    @JsonProperty(value = "updated_at")
    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "UTC")
    private Instant updatedAt;
}
