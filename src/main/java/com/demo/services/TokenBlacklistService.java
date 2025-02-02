package com.demo.services;

import com.demo.domain.tokenBlacklist.TokenBlacklist;
import com.demo.repositories.TokenBlacklistRepository;
import com.demo.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TokenBlacklistService {

    @Autowired
    private TokenBlacklistRepository blacklistRepository;

    @Autowired
    private JwtUtils jwtUtils;

    public Boolean validateIfTokenIsNotBlacklisted(String token){
        return blacklistRepository.existsByToken(token);
    }

    public void deleteBlacklistedToken(String token){
        blacklistRepository.deleteByToken(token);
    }

    public void blacklistToken(String token){
        TokenBlacklist tokenBlacklist = TokenBlacklist.builder()
                .expire_time(jwtUtils.getTokenExpireTime(token))
                .token(token).build();

        blacklistRepository.save(tokenBlacklist);
    }

    public void deleteAllBlacklistedTokens(List<String> tokens){
        blacklistRepository.deleteAllByTokenNamesNative(tokens);
    }

    public List<String> findTokensByExpireDate(){
        List<TokenBlacklist> tokens = blacklistRepository.findByExpireDatenativeCode(LocalDateTime.now());
        return tokens.stream().map(TokenBlacklist::getToken).toList();
    }


}
