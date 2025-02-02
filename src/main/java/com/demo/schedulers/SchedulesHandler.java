package com.demo.schedulers;


import com.demo.schedulers.schedules.AuctionAutomaticEnd;
import com.demo.schedulers.schedules.TokenBlacklistTimeDelete;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulesHandler {

    @Autowired
    private TokenBlacklistTimeDelete tokenBlacklistTimeDelete;

    @Autowired
    private AuctionAutomaticEnd auctionAutomaticEnd;

    @Scheduled(cron = "0 * * * * *")
    public void beginTokenBlackListStarter(){
        tokenBlacklistTimeDelete.beginProcess();
    }

    @Scheduled(cron = "30 * * * * *")
    public void beginAutomaticEndAuction(){
        auctionAutomaticEnd.beginProcess();
    }

}
