package com.black.mulberry.core.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * JwtTokenUtil is a utility class that provides methods to generate, validate and retrieve data from JSON Web Tokens.
 */
@Component
@Slf4j
public class JwtTokenUtil {

    /**
     * The secret key used to sign the JWT token.
     */
    @Value("${jwt.secret}")
    private String secret;

    /**
     * The time, in seconds, until the token expires.
     */
    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * Returns the username stored in the JWT token.
     *
     * @param token The JWT token.
     * @return The username stored in the JWT token.
     */
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    /**
     * Returns the expiration date of the JWT token.
     *
     * @param token The JWT token.
     * @return The expiration date of the JWT token.
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    /**
     * Returns a specific claim stored in the JWT token.
     *
     * @param token          The JWT token.
     * @param claimsResolver The function used to retrieve the specific claim.
     * @param <T>            The type of the specific claim.
     * @return The specific claim stored in the JWT token.
     */
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Returns all the claims stored in the JWT token.
     *
     * @param token The JWT token.
     * @return All the claims stored in the JWT token.
     */
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Checks if the JWT token is expired.
     *
     * @param token The JWT token.
     * @return true if the JWT token is expired, false otherwise.
     */
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * Generates a JWT token for a specific user.
     *
     * @param email The email of the user.
     * @return A JWT token for the user.
     */
    public String generateToken(String email) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, email);
    }

    /**
     * Generates a JWT token for a specific user with the specified claims.
     *
     * @param claims The claims to store in the JWT token.
     * @param email  The email of the user.
     * @return A JWT token for the user with the specified claims.
     */
    private String doGenerateToken(Map<String, Object> claims, String email) {
        final Date createdDate = new Date();
        final Date expirationDate = calculateExpirationDate(createdDate);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * Validates a JWT token for a specific user.
     *
     * @param token The JWT token.
     * @param email The email of the user.
     * @return true if the JWT token is valid for the user, false otherwise.
     */
    public Boolean validateToken(String token, String email) {
        try {
            final String username = getUsernameFromToken(token);
            return (
                    username.equals(email)
                            && !isTokenExpired(token));
        } catch (Exception e) {
            log.error("Error validating token: " + e.getMessage());
            return false;
        }
    }

    /**
     * Calculates the expiration date of a JWT token.
     *
     * @param createdDate The date the JWT token was created.
     * @return The expiration date of the JWT token.
     */
    private Date calculateExpirationDate(Date createdDate) {
        return new Date(createdDate.getTime() + expiration * 1000);
    }
}

