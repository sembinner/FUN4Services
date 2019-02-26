package com.fun4.authservice.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private static JwtTokenProvider instance;

    public static JwtTokenProvider getInstance(){
        if(instance == null){
            instance = new JwtTokenProvider();
        }
        return instance;
    }
    private String secretKey = "secret";

    private long validityInMilliseconds = 86400000;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String username, String role) {
        Claims claims = Jwts.claims().setSubject(username);

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .claim("Role", role)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
