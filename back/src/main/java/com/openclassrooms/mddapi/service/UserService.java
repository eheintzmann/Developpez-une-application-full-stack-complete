package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.exception.user.NonExistingUserException;
import com.openclassrooms.mddapi.model.dto.UserDTO;
import com.openclassrooms.mddapi.model.entity.User;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final ConversionService conversionService;

    public UserService(
            UserRepository userRepository,
            ConversionService conversionService
    ){
        this.userRepository = userRepository;
        this.conversionService = conversionService;
    }

    @Override
    public UserDTO getProfile(Principal principal) {
        User user = userRepository
                .findByEmail(principal.getName())
                .orElseThrow(() -> new NonExistingUserException("Cannot find user {}" + principal.getName()));

        return conversionService.convert(user, UserDTO.class);
    }

    @Override
    public UserDTO updateProfile(Principal principal, String username, String email) {
        User user = userRepository
                .findByEmail(principal.getName())
                .orElseThrow(() -> new NonExistingUserException("Cannot find user {}" + principal.getName()));

        user.setUsername(username);
        user.setEmail(email);

        userRepository.saveAndFlush(user);

        return conversionService.convert(user, UserDTO.class);
    }
}
