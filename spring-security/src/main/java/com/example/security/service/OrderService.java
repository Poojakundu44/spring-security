package com.example.security.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OrderService {

    private final Random random;


    public OrderService(Random random) {
        this.random = random;
    }


    public String fetchOrders(){
        if(random.nextBoolean()){
            throw new RuntimeException("Simulated service failure");
        }
        return "Order fetched successfully";
    }
}
