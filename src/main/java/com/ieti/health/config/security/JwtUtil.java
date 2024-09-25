package com.ieti.health.config.security;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ieti.health.controller.auth.TokenDto;
import com.ieti.health.repository.User.RoleEnum;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import static com.ieti.health.utils.Constants.CLAIMS_ROLES_KEY;

@Component
public class JwtUtil {

    private final JwtConfig jwtConfig;
    public JwtUtil(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public TokenDto generateToken(String username, List<RoleEnum> roles) {

        Date expirationDate = jwtConfig.getExpirationDate();
        String token = Jwts.builder().subject(username)
                .issuedAt(new Date())
                .expiration(expirationDate)
                .claim(CLAIMS_ROLES_KEY, roles)
                .signWith(jwtConfig.getSigningKey())
                .compact();
        return new TokenDto(token, expirationDate);
    }

    public Claims extractAndVerifyClaims(String token) {
        return Jwts.parser()
                .verifyWith(jwtConfig.getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


}
