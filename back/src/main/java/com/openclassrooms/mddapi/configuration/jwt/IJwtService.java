package com.openclassrooms.mddapi.configuration.jwt;

import com.openclassrooms.mddapi.model.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * IJwtService interface
 */
public interface IJwtService extends UserDetailsService {

    /**
     * Create a JSON Web Token
     *
     * @param user User
     * @return Access Token
     */
    String generateAccessToken(User user);


    /**
     * Verify a given JSON Web Token
     *
     * @param token String
     * @return boolean
     */
    boolean validateAccessToken(String token);

    /**
     * Load a User using his token
     *
     * @param token String
     * @return UserDetails
     */
    UserDetails loadUserByToken(String token);

}
