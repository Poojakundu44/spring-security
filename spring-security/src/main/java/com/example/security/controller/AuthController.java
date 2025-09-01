package com.example.security.controller;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/auth")
public class AuthController {


    private final String SECRET_KEY = "my-secret-key";

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username ,@RequestParam String password){
        if("admin".equals(username) && "password".equals(password)){
            String token = Jwts.builder().setSubject(username)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() +1000 *60*30))
                    .signWith(SignatureAlgorithm.HS256 , SECRET_KEY.getBytes())
                    .compact();


            return ResponseEntity.ok(token);
        }

        return ResponseEntity.status(401).body("Invalid credentials");
    }
}
