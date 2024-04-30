package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.exception.user.InvalidUserException;
import com.openclassrooms.mddapi.exception.user.NotUniqueEmailException;
import com.openclassrooms.mddapi.exception.user.NotUniqueUsernameException;
import com.openclassrooms.mddapi.model.dto.UserDTO;
import com.openclassrooms.mddapi.model.entity.User;
import com.openclassrooms.mddapi.repository.UserRepository;
import java.util.Objects;
import org.springframework.core.convert.ConversionService;
import org.springframework.dao.DataIntegrityViolationException;
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
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.conversionService = conversionService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO getProfile(UserDetails userDetails) {
        User user = userRepository
                .findById(Long.parseLong(userDetails.getUsername()))
                .orElseThrow(() -> new InvalidUserException("Cannot find user " + userDetails.getUsername()));

        return conversionService.convert(user, UserDTO.class);
    }

    @Override
    public UserDTO updateProfile(UserDetails userDetails, String username, String email, String password) {
        User user = userRepository
                .findById(Long.parseLong(userDetails.getUsername()))
                .orElseThrow(() -> new InvalidUserException("Cannot find user " + userDetails.getUsername()));

        if (username != null && !Objects.equals(username, user.getUsername())) {
            user.setUsername(username);

            try {
                userRepository.save(user);
            } catch (DataIntegrityViolationException ex) {

                if (userRepository.existsByUsername(username)) {
                    throw new NotUniqueUsernameException("username " + username + " already registered");
                }

                throw ex;
            }
        }

        if (email != null && !Objects.equals(email, user.getEmail())) {
            user.setEmail(email);

            try {
                userRepository.save(user);
            } catch (DataIntegrityViolationException ex) {

                if (userRepository.existsByEmail(email)) {
                    throw new NotUniqueEmailException("email " + email + " already registered");
                }

                throw ex;
            }
        }

        if (password != null) {
            user.setPassword(passwordEncoder.encode(password));
            userRepository.save(user);
        }

        userRepository.flush();

        return conversionService.convert(user, UserDTO.class);
    }
}
