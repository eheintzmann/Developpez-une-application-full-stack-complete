package com.openclassrooms.mddapi.model.payload.response.user;

import com.openclassrooms.mddapi.model.payload.response.Response;
import lombok.Builder;
import lombok.Data;

/**
 * Profile Response
 */
@Data
@Builder
public class ProfileResponse implements Response {

    private Long id;

    private String email;

    private String username;

}
