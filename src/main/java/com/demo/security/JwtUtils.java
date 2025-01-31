package com.demo.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.servlet.http.Cookie;

import java.nio.file.AccessDeniedException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class JwtUtils {

    private final String SECRET_KEY = "very secure key, trust me, i promise :)";
    private final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY);
    private final String ISSUER = "AUCTION_SYSTEM - BY HENRIQUE SILVEIRA";

    private final Long MAX_EXPIRE_DAYS = 30L;

    public String createNewToken(String mail){
        return JWT.create()
                .withIssuer(ISSUER)
                .withIssuedAt(Instant.now())
                .withExpiresAt(returnExpireDate())
                .withSubject(mail)
                .sign(ALGORITHM);

    }

    public String getTokenSubject(String token){
        return JWT.require(ALGORITHM)
                .withIssuer(ISSUER)
                .build()
                .verify(token)
                .getSubject();
    }


    public Instant returnExpireDate(){
        return ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).plusDays(MAX_EXPIRE_DAYS).toInstant();
    }

}
