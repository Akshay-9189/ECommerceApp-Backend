package com.csi.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    // Use can use any secrete (Used for encoding purpose)
    private final String secret = "akshay@123";

    //pass the jwt token this method will give exact username
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    //pass the jwt token this method will give expiration date
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    //method providing claim
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    //For getting any data from token secret key will require
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    //Check if token expired
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    //For generating token
    public String generateToken(String userEmail) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userEmail);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))   // 10 Hour
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    //validating token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String userEmail = extractUsername(token);
        return (userEmail.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
