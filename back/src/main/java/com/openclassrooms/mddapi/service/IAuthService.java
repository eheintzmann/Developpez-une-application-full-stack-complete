package com.openclassrooms.mddapi.service;

public interface IAuthService {

    /**
     * Register a new user
     *
     * @param email user email
     * @param name username
     * @param password user password
     * @return JWT token
     */
    String registerUser(String email, String name, String password);

    /**
     * Log in an existing user
     *
     * @param email user email
     * @param password user password
     * @return JWT token
     */
    String loginUser(String email, String password);

}
