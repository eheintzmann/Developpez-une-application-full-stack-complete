package com.openclassrooms.mddapi.model.payload.request.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * MessageRequest DTO
 */
@Data
public class MessageRequest {

    private String message;

    @JsonProperty(value = "user_id")
    private int userId;

    @JsonProperty(value = "rental_id")
    private int rentalId;
}
