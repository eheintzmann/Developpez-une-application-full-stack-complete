package com.openclassrooms.mddapi.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.openclassrooms.mddapi.configuration.serializer.ApiInstantSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper registerObjectMapper(){
        ObjectMapper mapper = new ObjectMapper()
                .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        SimpleModule module = new SimpleModule("ApiInstantSerializer");
        module.addSerializer(Instant.class, new ApiInstantSerializer());
        mapper.registerModule(module);

        return mapper;
    }
}
