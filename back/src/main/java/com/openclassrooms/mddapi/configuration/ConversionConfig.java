package com.openclassrooms.mddapi.configuration;

import com.openclassrooms.mddapi.configuration.converter.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;

import java.util.HashSet;

/**
 * Configuration class for Conversion service
 * @see <a href="https://docs.spring.io/spring-framework/reference/core/validation/convert.html#core-convert-Spring-config">Configuring a ConversionService</a>
 */
@Configuration
public class ConversionConfig {

    /**
     * New ConversionService instance
     * @see <a href="https://docs.spring.io/spring-framework/reference/core/validation/convert.html">Spring Type Conversion</a>
     *
     * @return ConversionServiceFactoryBean
     */
    @Bean
    public ConversionServiceFactoryBean conversionService() {
        ConversionServiceFactoryBean conversionService = new ConversionServiceFactoryBean();
        HashSet<Converter<?, ?>> converters = new HashSet<>();

        converters.add(new UserEntityToUserDTO());
        converters.add(new UserDTOToAuthMeResponse());
        converters.add(new TopicEntityToTopicDTO());
        converters.add(new UserDTOToProfileResponse());
        converters.add(new PostEntityToPostDTO());
        converters.add(new PostEntitiesToPostsDTO());
        converters.add(new TopicEntityToSubscriptionDTO());
        converters.add(new PostEntityToPostWitCommentsDTO());

        conversionService.setConverters(converters);
        return conversionService;
    }
}
