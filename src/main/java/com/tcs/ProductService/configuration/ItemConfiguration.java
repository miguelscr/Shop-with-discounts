package com.tcs.ProductService.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ItemConfiguration {
    @Bean("ClientRest")
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
