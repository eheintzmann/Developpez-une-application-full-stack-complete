package com.openclassrooms.mddapi.configuration.converter;

import com.openclassrooms.mddapi.model.dto.UserDTO;
import com.openclassrooms.mddapi.model.payload.response.user.ProfileIResponse;
import org.springframework.core.convert.converter.Converter;

/**
 * Convert UserDTO to Profile Response
 */
public class UserDTOToProfileResponse implements Converter<UserDTO, ProfileIResponse> {

    /**
     * Convert UserDTO to ProfileResponse
     *
     * @param userDTO UserDTO
     * @return ProfileResponse
     */
    @Override
    public ProfileIResponse convert(UserDTO userDTO) {

        return ProfileIResponse.builder()
                .id(userDTO.getId())
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .build();
    }

}
