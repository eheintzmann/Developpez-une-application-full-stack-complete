package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.model.dto.UserDTO;
import com.openclassrooms.mddapi.model.entity.User;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

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
    public UserDTO getProfile(User user) {
        return conversionService.convert(user, UserDTO.class);
    }

    @Override
    public UserDTO updateProfile(User user, String username, String email) {
        user.setUsername(username);
        user.setEmail(email);

        userRepository.saveAndFlush(user);

        return conversionService.convert(user, UserDTO.class);
    }
}
