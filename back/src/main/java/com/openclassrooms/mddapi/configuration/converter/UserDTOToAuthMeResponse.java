package com.openclassrooms.mddapi.configuration.converter;

import com.openclassrooms.mddapi.model.dto.UserDTO;
import com.openclassrooms.mddapi.model.payload.response.auth.AuthMeIResponse;
import org.springframework.core.convert.converter.Converter;

/**
 * Convert UserDTO to AuthMeResponse
 */
public class UserDTOToAuthMeResponse implements Converter<UserDTO, AuthMeIResponse> {

    /**
     * Convert UserDTO to AuthMeResponse
     *
     * @param userDTO UserDTO
     * @return AuthMeResponse
     */
    @Override
    public AuthMeIResponse convert(UserDTO userDTO) {

        return AuthMeIResponse.builder()
                .id(userDTO.getId())
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .createdAt(userDTO.getCreatedAt())
                .updateAt(userDTO.getUpdatedAt())
                .build();
    }

}
