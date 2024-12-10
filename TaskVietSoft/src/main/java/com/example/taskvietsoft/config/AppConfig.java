package com.example.taskvietsoft.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
@Configuration
// convert từ DTO xang Entity
public class AppConfig {
    @Bean
    public ModelMapper
    modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new  RestTemplate();
    }
}
