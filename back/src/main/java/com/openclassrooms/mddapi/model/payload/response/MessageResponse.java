package com.openclassrooms.mddapi.model.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * MessageResponse DTO
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessageResponse implements Response {

    private String message;
}
