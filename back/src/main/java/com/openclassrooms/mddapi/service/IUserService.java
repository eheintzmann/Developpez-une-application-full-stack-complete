package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.model.dto.UserDTO;

import java.security.Principal;

public interface IUserService {

    UserDTO getProfile(Principal principal);

    UserDTO updateProfile(Principal principal, String username, String email);

}
