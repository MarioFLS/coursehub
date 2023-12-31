package com.coursehub.application.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.coursehub.application.domain.entities.User;
import com.coursehub.application.infra.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collections;

@Service
public class AuthenticationService {
    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withIssuer("CourseHub")
                .withSubject(user.getName())
                .withClaim("id", user.getId().toString())
                .withClaim("email", user.getEmail())
                .withClaim("role", "ROLE_USER")
                .withExpiresAt(generateExpirationDate())
                .sign(algorithm);
    }

    public CustomUserDetails validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            DecodedJWT decodedJWT = JWT.require(algorithm)
                    .withIssuer("CourseHub")
                    .build()
                    .verify(token);

            String userId = decodedJWT.getClaim("id").asString();
            String username = decodedJWT.getSubject();

            return new CustomUserDetails(userId, username, Collections.emptyList());
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    private Instant generateExpirationDate(){
        return LocalDateTime.now()
                .plusHours(2)
                .toInstant(ZoneOffset.of("-03:00"));
    }
}
