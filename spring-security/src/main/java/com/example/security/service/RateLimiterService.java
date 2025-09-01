package com.example.security.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RateLimiterService {


    private final StringRedisTemplate redisTemplate;


    public RateLimiterService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public boolean allowRequest(String key){
        String redisKey = "rateLimit:" +key;
        Long requests = redisTemplate.opsForValue().increment(redisKey);


        if( requests!=null && requests == 1){
            redisTemplate.expire(redisKey , Duration.ofSeconds(60));
        }
        return requests!=null && requests <= 5;

    }


}
