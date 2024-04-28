package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.model.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUserService {

    /**
     *
     * @param userDetails User details
     * @return UserDTO
     */
    UserDTO getProfile(UserDetails userDetails);

    /**
     *
     * @param userDetails  User details
     * @param username String
     * @param email String
     * @param password String
     * @return UserDTO
     */
    UserDTO updateProfile(UserDetails userDetails, String username, String email, String password);

}
