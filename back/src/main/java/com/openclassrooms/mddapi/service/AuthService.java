package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.configuration.jwt.IJwtService;
import com.openclassrooms.mddapi.exception.user.*;
import com.openclassrooms.mddapi.model.entity.User;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Auth service
 */
@Service
public class AuthService implements IAuthService {
    private final IJwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    /**
     * Constructor for AuthentificationService class
     *
     * @param jwtService      JwtService
     * @param passwordEncoder PasswordEncoder
     * @param userRepository  UserRepository
     */
    @Autowired
    AuthService(
            IJwtService jwtService,
            PasswordEncoder passwordEncoder,
            UserRepository userRepository
    ) {
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    /**
     * Register a new user
     *
     * @param email    user email
     * @param username     username
     * @param password user password
     * @return String token
     */
    @Override
    public String registerUser(String email, String username, String password) {

        // If user doesn't exist, register it

        // Create new user
        User user = User.builder()
                .username(username)
                .email(email)
                .password(passwordEncoder.encode(password))
                .build();

        try {
            // Save user in db
            userRepository.saveAndFlush(user);
        } catch (DataIntegrityViolationException ex) {

            if (userRepository.existsByEmail(email)) {
                throw new NotUniqueEmailException("email " + email + " already registered");
            }

            if (userRepository.existsByUsername(username)) {
                throw new NotUniqueUsernameException("username " + username + " already registered");
            }

            throw ex;
        }

        // Return token
        return jwtService.generateAccessToken(user);
    }

    /**
     * Log in an existing user
     *
     * @param login    user email
     * @param password user password
     * @return String token
     */
    @Override
    public String loginUser(String login, String password) {

        User user = userRepository
                .findByEmail(login)
                .orElseGet(
                        () -> userRepository.findByUsername(login)
                                .orElseThrow(
                                        () -> new InvalidUserException("Cannot find user " + login)
                                )
                );

        // if user  authenticated
        if (passwordEncoder.matches(password, user.getPassword())) {
            // Return token
            return jwtService.generateAccessToken(user);
        }
        throw new CannotAuthenticateUserException("Cannot authenticate user " + login);
    }

}
