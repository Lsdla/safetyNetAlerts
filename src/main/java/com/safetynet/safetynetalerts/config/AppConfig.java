package com.safetynet.safetynetalerts.config;

import org.modelmapper.ModelMapper;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Yahia CHERIFI
 * The application configuration class.
 */
@Configuration
public class AppConfig {

    /**
     * ModelMapper bean.
     * used mainly in the conversion process
     * from entity to DTO
     * @return new modelMapper
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    /**
     * HttpTraceRepository bean.
     * used by the httptrace actuator
     * @return InMemoryHttpTraceRepository()
     */
    @Bean
    public HttpTraceRepository httpTraceRepository() {
        return new InMemoryHttpTraceRepository();
    }
}
