package com.openclassrooms.mddapi.model.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TokenResponse DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponse implements Response {

    private String token;
}
