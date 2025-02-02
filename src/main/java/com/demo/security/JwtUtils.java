package com.demo.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.demo.domain.tokenBlacklist.TokenBlacklist;
import com.demo.repositories.TokenBlacklistRepository;
import jakarta.servlet.http.Cookie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.nio.file.AccessDeniedException;
import java.security.MessageDigest;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.util.Arrays;

@Service
@Slf4j
public class JwtUtils {

    private final String SECRET_KEY = "very secure key, trust me, i promise :)";
    private final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY);
    private final String ISSUER = "AUCTION_SYSTEM - BY HENRIQUE SILVEIRA";

    private final Long MAX_EXPIRE_DAYS = 30L;

    @Autowired
    private TokenBlacklistRepository blacklistRepository;

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

    public LocalDateTime getTokenExpireTime(String token){
        return JWT.require(ALGORITHM)
                .withIssuer(ISSUER)
                .build()
                .verify(token)
                .getExpiresAtAsInstant().atZone(ZoneId.of("America/Sao_Paulo")).toLocalDateTime();
    }

    public Instant returnExpireDate(){
        return ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).plusDays(MAX_EXPIRE_DAYS).toInstant();
    }

}
