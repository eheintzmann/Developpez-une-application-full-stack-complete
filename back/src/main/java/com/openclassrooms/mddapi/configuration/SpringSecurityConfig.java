package com.openclassrooms.mddapi.configuration;

import com.openclassrooms.mddapi.configuration.jwt.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

/**
 * Configuration class for Spring Security
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
    private final JwtFilter jwtFilter;

    /**
     * Constructor for SpringSecurityConfig class
     *
     * @param jwtFilter JwtFilter
     */
    public SpringSecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    /**
     * New PasswordEncoder instance
     *
     * @return BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * New AuthenticationManager instance
     *
     * @param authConfig AuthenticationManager instance
     * @return AuthenticationManager
     * @throws Exception Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    /**
     * New SecurityFilterChain instance
     *
     * @param http HttpSecurity
     * @return SecurityFilterChain
     * @throws Exception Exception
     */
    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http,
            HandlerExceptionResolver handlerExceptionResolver
    ) throws Exception {

        // Use stateless session; session won't be used to store user's state.
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // CSRF is not needed
        http.csrf(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests(requestMatcherRegistry -> requestMatcherRegistry
                // Do not authenticate these requests
                .requestMatchers(
                        antMatcher(HttpMethod.POST, "/api/v1/auth/login"),
                        antMatcher(HttpMethod.POST, "/api/v1/auth/register")
                ).permitAll()
                // Authenticate these requests
                .requestMatchers(
                        antMatcher("/api/v1/**")
                ).authenticated()
                // Deny all other requests
                .anyRequest().denyAll()
        );

        // Send Http status 401 when error occurs
        http.exceptionHandling(
                handlingConfigurer -> handlingConfigurer
                        .authenticationEntryPoint(
                                (request, response, authException) -> handlerExceptionResolver.resolveException(
                                        request,
                                        response,
                                        null,
                                        authException
                                )
                        )
                        .accessDeniedHandler((request, response, accessDeniedException) -> handlerExceptionResolver.resolveException(
                                        request,
                                        response,
                                        null,
                                        accessDeniedException
                                )
                        )
        );

        // Add a filter to validate the tokens with every authenticated request
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
