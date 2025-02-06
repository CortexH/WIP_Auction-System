package com.demo.schedulers;


import com.demo.schedulers.schedules.AuctionAutomaticEnd;
import com.demo.schedulers.schedules.TokenBlacklistTimeDelete;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SchedulesHandler {

    private final TokenBlacklistTimeDelete tokenBlacklistTimeDelete;
    private final AuctionAutomaticEnd auctionAutomaticEnd;

    @Scheduled(cron = "0 * * * * *")
    public void beginTokenBlackListStarter(){
        tokenBlacklistTimeDelete.beginProcess();
    }

    @Scheduled(cron = "30 * * * * *")
    public void beginAutomaticEndAuction(){
        auctionAutomaticEnd.beginProcess();
    }
}
