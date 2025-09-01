package com.example.security.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class Resilience4jConfig {


    @Bean
    public CircuitBreakerRegistry circuitBreakerRegistry(){
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(70)
                .waitDurationInOpenState(Duration.ofSeconds(5))
                .slidingWindowSize(10)
                .build();

        return CircuitBreakerRegistry.of(config);

    }
}
