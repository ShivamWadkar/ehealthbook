package com.ehealthbook.authentication.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey; // JWT secret key

    @Value("${jwt.expiration}")
    private long jwtExpiration; // JWT expiration time in milliseconds

    // Generate JWT Token
    public String generateToken(UUID userId, String email, String role) {
        return Jwts.builder()
                .setSubject(String.valueOf(userId)) // Set "sub" claim (userId in this case)
                .claim("email", email) // Add custom "username" claim
                .claim("roles", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes(StandardCharsets.UTF_8))
                .compact();
    }

    // Extract username from JWT Token
    public String extractUserId(String token) {
        return extractClaims(token).getSubject();
    }

    // Extract claims from JWT Token
    private Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Validate JWT Token
    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    // Validate JWT Token
    public boolean validateToken(String token, UUID userId) {
        return (userId.equals(UUID.fromString(extractUserId(token))) && !isTokenExpired(token));
    }
}
