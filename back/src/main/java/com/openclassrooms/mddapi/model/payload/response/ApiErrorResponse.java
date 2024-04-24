package com.openclassrooms.mddapi.model.payload.response;

import lombok.Builder;
import lombok.Data;

/**
 * MessageResponse DTO
 */

@Data
@Builder
public class ApiErrorResponse implements IResponse {

    private String url;

    private String message;
}
