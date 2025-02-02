package com.demo.services;

import com.demo.domain.auctionHistory.AuctionHistory;
import com.demo.dto.internal.NewHistoryDTO;
import com.demo.repositories.AuctionHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuctionHistoryService {

    @Autowired
    private AuctionHistoryRepository historyRepository;

    public void createNewHistory(NewHistoryDTO data){
        try{
            AuctionHistory history = AuctionHistory.builder()
                    .auction_id(data.auction())
                    .amout(data.amout())
                    .event_type(data.eventType())
                    .user_id(data.user())
                    .timeStamp(LocalDateTime.now())
                    .message(data.message())
                    .build();

            historyRepository.save(history);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
