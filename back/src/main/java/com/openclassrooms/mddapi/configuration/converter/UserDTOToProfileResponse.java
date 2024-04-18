package com.openclassrooms.mddapi.configuration.converter;

import com.openclassrooms.mddapi.model.dto.UserDTO;
import com.openclassrooms.mddapi.model.payload.response.user.ProfileResponse;
import org.springframework.core.convert.converter.Converter;

/**
 * Convert UserDTO to Profile Response
 */
public class UserDTOToProfileResponse implements Converter<UserDTO, ProfileResponse> {

    /**
     * Convert UserDTO to ProfileResponse
     *
     * @param userDTO UserDTO
     * @return ProfileResponse
     */
    @Override
    public ProfileResponse convert(UserDTO userDTO) {

        return ProfileResponse.builder()
                .id(userDTO.getId())
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .build();
    }

}
