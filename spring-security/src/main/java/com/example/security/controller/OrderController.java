package com.example.security.controller;

import com.example.security.service.OrderService;
import com.example.security.service.RateLimiterService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {


    private final OrderService orderService;
    private final RateLimiterService rateLimiterService;

    public OrderController(OrderService orderService, RateLimiterService rateLimiterService) {
        this.orderService = orderService;
        this.rateLimiterService = rateLimiterService;
    }


    @GetMapping("/orders")
    @CircuitBreaker(name="orderService" , fallbackMethod = "fallbackOrders")
    public String getOrders(){

        if(!rateLimiterService.allowRequest("orders")){
            return "Rate limit exceeded. Please try later.";
        }

        return orderService.fetchOrders();

    }

}
