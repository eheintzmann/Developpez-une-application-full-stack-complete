package com.openclassrooms.mddapi.configuration.converter;

import com.openclassrooms.mddapi.model.dto.UserDTO;
import com.openclassrooms.mddapi.model.payload.response.auth.AuthMeResponse;
import org.springframework.core.convert.converter.Converter;

/**
 * Convert UserDTO to AuthMeResponse
 */
public class UserDTOToAuthMeResponse implements Converter<UserDTO, AuthMeResponse> {

    /**
     * Convert UserDTO to AuthMeResponse
     *
     * @param userDTO UserDTO
     * @return AuthMeResponse
     */
    @Override
    public AuthMeResponse convert(UserDTO userDTO) {

        return AuthMeResponse.builder()
                .id(userDTO.getId())
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .createdAt(userDTO.getCreatedAt())
                .updateAt(userDTO.getUpdatedAt())
                .build();
    }

}
