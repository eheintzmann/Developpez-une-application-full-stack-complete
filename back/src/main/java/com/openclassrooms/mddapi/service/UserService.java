package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.exception.user.NonExistingUserException;
import com.openclassrooms.mddapi.model.dto.UserDTO;
import com.openclassrooms.mddapi.model.entity.User;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final ConversionService conversionService;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            ConversionService conversionService,
            PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.conversionService = conversionService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO getProfile(UserDetails userDetails) {
        User user = userRepository
                .findById(Long.parseLong(userDetails.getUsername()))
                .orElseThrow(() -> new NonExistingUserException("Cannot find user " + userDetails.getUsername()));

        return conversionService.convert(user, UserDTO.class);
    }

    @Override
    public UserDTO updateProfile(UserDetails userDetails, String username, String email, String password) {
        User user = userRepository
                .findById(Long.parseLong(userDetails.getUsername()))
                .orElseThrow(() -> new NonExistingUserException("Cannot find user " + userDetails.getUsername()));

        if (username != null) {
            user.setUsername(username);
        }
        if (email != null) {
            user.setEmail(email);
        }
        if (password != null) {
            user.setPassword(passwordEncoder.encode(password));
        }

        userRepository.saveAndFlush(user);


        return conversionService.convert(user, UserDTO.class);
    }
}
