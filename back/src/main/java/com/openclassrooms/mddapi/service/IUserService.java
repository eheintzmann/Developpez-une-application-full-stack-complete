package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.model.dto.UserDTO;
import com.openclassrooms.mddapi.model.entity.User;

public interface IUserService {

    /**
     *
     * @param user User
     * @return UserDTO
     */
    UserDTO getProfile(User user);

    /**
     *
     * @param user User
     * @param username String
     * @param email String
     * @param password String
     * @return UserDTO
     */
    UserDTO updateProfile(User user, String username, String email, String password);

}
