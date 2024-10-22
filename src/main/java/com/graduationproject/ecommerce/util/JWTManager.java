package com.graduationproject.ecommerce.util;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class JWTManager {
    @Value("${jwt.key}")
    private String secretKey;

    public String generateToken(Long id) {
        Long duration = 1000 * 60 * 60 * 24l;
        return JWT.create()
                .withClaim("id", id)
                .withIssuer("graduationproject.com")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + duration))
                .sign(Algorithm.HMAC512(secretKey));
    }

    public Optional<Long> validToken(String token) {

        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC512(secretKey)).withIssuer("graduationproject.com")
                    .build();
            DecodedJWT decodedJWT = jwtVerifier.verify(token);

            return Optional.of(decodedJWT.getClaim("id").asLong());
        } catch (JWTVerificationException e) {
            e.getMessage();
        }
        return Optional.empty();
    }
}
