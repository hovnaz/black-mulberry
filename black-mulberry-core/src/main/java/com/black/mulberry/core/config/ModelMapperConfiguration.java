package com.black.mulberry.core.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * A configuration class for the ModelMapper.
 */
@Configuration
public class ModelMapperConfiguration {

    /**
     * Creates a new instance of the ModelMapper.
     *
     * @return a new ModelMapper instance
     */
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        // Set the matching strategy and other configuration options
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);

        return mapper;
    }
}
