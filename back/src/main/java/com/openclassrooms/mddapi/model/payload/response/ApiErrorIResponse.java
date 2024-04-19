package com.openclassrooms.mddapi.model.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * MessageResponse DTO
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ApiErrorIResponse implements IResponse {

    private String message;
}
