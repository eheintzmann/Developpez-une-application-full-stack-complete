package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.configuration.ConversionConfig;
import com.openclassrooms.mddapi.configuration.jwt.JwtService;
import com.openclassrooms.mddapi.exception.user.CannotAuthenticateUserException;
import com.openclassrooms.mddapi.exception.user.AlreadyExitingUserException;
import com.openclassrooms.mddapi.model.dto.UserDTO;
import com.openclassrooms.mddapi.model.entity.User;
import com.openclassrooms.mddapi.repository.UserRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Authentification service
 */
@Slf4j
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
     * @param authManager     AuthenticationManager
     * @param jwtService      JwtService
     * @param passwordEncoder PasswordEncoder
     * @param userRepository  UserRepository
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
     * @param email    user email
     * @param name     username
     * @param password user password
     * @return String token
     */
    @Override
    public String registerUser(String email, String name, String password) {

        // If user exists, stop registration, do not return a token
        if (userRepository.existsByEmailOrUsername(email, name)) {
            throw new AlreadyExitingUserException("Email (" + email + ") or username ("  +name + ") is already used");
        }

        // If user doesn't exist, register it

        // Create new user
        User user = User.builder()
                .username(name)
                .email(email)
                .password(passwordEncoder.encode(password))
                .build();

        // Save user in db
        userRepository.saveAndFlush(user);

        // Return token
        return jwtService.generateAccessToken(user);
    }

    /**
     * Log in an existing user
     *
     * @param login  user email
     * @param password user password
     * @return String token
     */
    @Override
    public String loginUser(String login, String password) {

        User user = userRepository
                .findByEmail(login)
                .orElseThrow(() -> new CannotAuthenticateUserException("Cannot authenticate user " + login));

        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getId(), password)
        );

        // if user  authenticated
        if (authentication.isAuthenticated()) {
            // Return token
            return jwtService.generateAccessToken(user);
        }
        throw new CannotAuthenticateUserException("Cannot authenticate user " + login);
    }

    /**
     * Return details of a given user
     *
     * @param user User
     * @return Optional User
     */
    @Override
    public UserDTO authUser(User user) {

        return conversionService.convert(user, UserDTO.class);
    }

}
