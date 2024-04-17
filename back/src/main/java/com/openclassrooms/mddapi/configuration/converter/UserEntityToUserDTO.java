package com.openclassrooms.mddapi.configuration.converter;

import com.openclassrooms.mddapi.model.dto.UserDTO;
import com.openclassrooms.mddapi.model.entity.User;
import org.springframework.core.convert.converter.Converter;

/**
 * Convert User to UserDTO
 */
public class UserEntityToUserDTO implements Converter<User, UserDTO> {

    /**
     * Convert User to UserDTO
     *
     * @param user User
     * @return UserDto
     */
    @Override
    public UserDTO convert(User user) {

        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

}
