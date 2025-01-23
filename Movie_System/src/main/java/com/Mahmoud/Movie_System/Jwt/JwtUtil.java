package com.Mahmoud.Movie_System.Jwt;


import com.Mahmoud.Movie_System.Dto.TokenDto;
import com.Mahmoud.Movie_System.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private static Key securityKey ;
    private static final String SECRET_KEY ="mkloifbabsmckosijdhuqywyegrffdfqwettbcvgftcuiqkwjnfhuurymmmrrkluliuthnj";
    private static final long EXPIRATION_TIME = 3600000;


    public JwtUtil() throws NoSuchAlgorithmException {
        securityKey = getSecretKey();
    }

    private SecretKey getSecretKey() {
        byte[] keyBytes = Base64.getDecoder().decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Map<String, Object> generateClaims(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole());
        claims.put("userId", user.getId());
        claims.put("email", user.getEmail());
        return claims;

    }
    public TokenDto generateToken(User user) {
         String accessToken = Jwts.builder().setClaims(generateClaims(user))
                .setSubject(user.getUsername())               // Set the username as the subject
                .setIssuedAt(new Date())                      // Set the issue time
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hour expiration
                .signWith(securityKey) // Sign the token with HMAC-SHA256
                .compact();
        String refreshToken = Jwts.builder()
                .setClaims(generateClaims(user))
                .setSubject(user.getUsername())               // Set the username as the subject// Add the role as a custom claim
                .setIssuedAt(new Date())                      // Set the issue time
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day expiration
                .signWith(securityKey ) // Sign the token with HMAC-SHA256
                .compact();
        TokenDto token = new TokenDto();
        token.setAccessToken(accessToken);
        token.setRefreshToken(refreshToken);
        token.setRole(extractRole(accessToken));

        return token;
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(securityKey)
                .build().parseClaimsJws(token).getBody();
    }


    // to extract the username from the token
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(securityKey)               // Use the secret key for validation
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

    }

    //to extract the role from the token
    public String extractRole(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(securityKey)               // Use the secret key for validation
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("role", String.class);       // Retrieve the role claim
    }


    public Date extractExpirationDate(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(securityKey)               // Use the secret key for validation
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();      // Retrieve the role claim
    }

    // Validate the token
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(securityKey)              // Verify with the secret key
                    .build()
                    .parseClaimsJws(token);                  // Parse the token
            return true; // Valid token
        } catch (JwtException | IllegalArgumentException e ) {
            return false; // Invalid token
        }

    }

    // Get the base64 encoded secret key (optional, for debugging or storage)
    public String getBase64EncodedKey() {
        return Base64.getEncoder().encodeToString(securityKey.getEncoded());
    }
}
