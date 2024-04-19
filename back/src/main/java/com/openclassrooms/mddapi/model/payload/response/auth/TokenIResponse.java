package com.openclassrooms.mddapi.model.payload.response.auth;

import com.openclassrooms.mddapi.model.payload.response.IResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TokenResponse
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenIResponse implements IResponse {

    private String token;
}
