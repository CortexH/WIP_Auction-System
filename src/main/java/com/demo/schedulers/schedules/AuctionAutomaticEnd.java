package com.demo.schedulers.schedules;

import com.demo.domain.auction.Auction;
import com.demo.services.AuctionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class AuctionAutomaticEnd {

    @Autowired
    private AuctionService auctionService;

    public void beginProcess(){
        try{
            log.info("AUTOMATIC CLOSE AUCTION PROCESS STARTED AT :: {}", LocalDateTime.now());
            List<Auction> auctions = auctionService.getAuctionsByLessTimeThanDateTime(null);
            if(auctions.isEmpty()){
                log.info("COULD NOT FOUND ANY CLOSEABLE AUCTION");
                endProcess(0);
            }



        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void endProcess(Integer num){
        log.info("NUMBER OF AUCTIONS CLOSED :: {}", num);
        log.info("FINISHED PROCESS OF AUTOMATIC CLOSE AUCTIONS");
    }

}
