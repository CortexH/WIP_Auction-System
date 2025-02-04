package com.demo.schedulers.schedules;

import com.demo.services.TokenBlacklistService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TokenBlacklistTimeDelete {

    private final TokenBlacklistService service;

    @Transactional
    public void beginProcess(){
        try{
            log.info("'ERASE BLACKLISTED EXPIRED TOKENS' PROCESS STARTED AT :: {}", LocalDateTime.now());
            List<String> tokens = service.findTokensByExpireDate();
            if(tokens.isEmpty()) {
                log.info("THE PROCESS DID NOT FOUND ANY EXPIRED TOKEN");
                finishProcess(0);
                return;
            }
            finishProcess(tokens.size());
            service.deleteAllBlacklistedTokens(tokens);

        } catch (Exception e) {
            log.info("AN ERROR OCURRED :: {}", e.getMessage());
            finishProcess(0);
        }
    }

    public void finishProcess(Integer tokensDeleted){
        log.info("TOKENS DELETED IN THE PROCESS :: {}", tokensDeleted);
        log.info("SERVICE FINISHED AT :: {}", LocalDateTime.now());
    }
}
