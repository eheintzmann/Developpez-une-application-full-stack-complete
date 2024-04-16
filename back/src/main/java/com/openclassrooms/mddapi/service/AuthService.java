package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.configuration.ConversionConfig;
import com.openclassrooms.mddapi.configuration.jwt.JwtService;
import com.openclassrooms.mddapi.exception.*;
import com.openclassrooms.mddapi.model.dto.UserDTO;
import com.openclassrooms.mddapi.model.entity.User;
import com.openclassrooms.mddapi.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Authentification service
 */
@Data
@Service
public class AuthService implements IAuthService {

    private final ConversionConfig conversionConfig;
    private final ConversionService conversionService;
    private AuthenticationManager authManager;
    private JwtService jwtService;
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private UserDetailsService userDetailsService;

    /**
     * Constructor for AuthentificationService class
     *
     * @param authManager AuthenticationManager
     * @param jwtService JwtService
     * @param passwordEncoder PasswordEncoder
     * @param userRepository UserRepository
     */
    @Autowired
    AuthService(
            AuthenticationManager authManager,
            JwtService jwtService,
            PasswordEncoder passwordEncoder,
            UserRepository userRepository,
            ConversionConfig conversionConfig, @Qualifier("conversionService") ConversionService conversionService) {
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.conversionConfig = conversionConfig;
        this.conversionService = conversionService;
    }

    /**
     * Register a new user
     *
     * @param email user email
     * @param name username
     * @param password user password
     * @return String token
     */
    @Override
    public String registerUser(String email, String name, String password) {

        // If user exists, stop registration, do not return a token
        if (userRepository.existsByEmail(email)) {
            throw new UserAlreadyExitsException(email + " is already used");
        }

        // If user doesn't exist, register it

        // Create new user
        User user = User.builder()
                .name(name)
                .email(email)
                .password(passwordEncoder.encode(password))
                .build();

        // Save user in db
        try {
            userRepository.saveAndFlush(user);
        } catch (Exception ex) {
            throw new CannotSaveException(ex.getMessage());
        }

        // Return token
        String token;
        try {
            token = jwtService.generateAccessToken(user);
        } catch (Exception ex) {
            throw new CannotGenerateTokenException(ex.getMessage());
        }
         return token;
    }

    /**
     * Log in an existing user
     *
     * @param email user email
     * @param password user password
     * @return String token
     */
    @Override
    public String loginUser(String email, String password) {

        Authentication authentication;
        try {
            authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
        } catch (AuthenticationException ex) {
            throw new CannotAuthenticateException(ex.getMessage());
        }

        User user = (User) authentication.getPrincipal();

        // Return token
        String token;
        try {
            token = jwtService.generateAccessToken(user);
        } catch (Exception ex) {
            throw new CannotGenerateTokenException(ex.getMessage());
        }
        return token;
    }

    /**
     * Return details of a given user
     *
     * @param email user email
     * @return Optional User
     */
    @Override
    public UserDTO authUser(String email) {

        Optional<User> optUser;
        try {
            optUser = this
                    .userRepository
                    .findByEmail(email);
        } catch(Exception ex) {
            throw new CannotReadException(ex.getMessage());
        }
        return optUser
                .map(user -> conversionService.convert(user, UserDTO.class))
                .orElseThrow(() -> new NotExistingUserException(email + " not found"));
    }

}
