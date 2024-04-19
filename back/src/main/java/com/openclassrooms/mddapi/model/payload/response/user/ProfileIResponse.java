package com.openclassrooms.mddapi.model.payload.response.user;

import com.openclassrooms.mddapi.model.payload.response.IResponse;
import lombok.Builder;
import lombok.Data;

/**
 * Profile Response
 */
@Data
@Builder
public class ProfileIResponse implements IResponse {

    private Long id;

    private String email;

    private String username;

}
