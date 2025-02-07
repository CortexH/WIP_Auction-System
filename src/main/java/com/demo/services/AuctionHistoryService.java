package com.demo.services;

import com.demo.domain.auction.Auction;
import com.demo.domain.auctionHistory.AuctionHistory;
import com.demo.dto.internal.NewAuctionHistoryDTO;
import com.demo.dto.output.FindBidsDTO;
import com.demo.dto.output.FindSingleAuctionHistoryDTO;
import com.demo.repositories.AuctionHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuctionHistoryService {

    private final AuctionHistoryRepository historyRepository;

    public void createNewHistoryByDataOrListOfData(NewAuctionHistoryDTO data){
        try{
            AuctionHistory history = AuctionHistory.builder()
                    .auction(data.auction())
                    .amount(data.amount())
                    .event_type(data.auctionEventType())
                    .user_id(data.user())
                    .timeStamp(LocalDateTime.now())
                    .message(data.message())
                    .build();

            historyRepository.save(history);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void createNewHistoryByDataOrListOfData(List<NewAuctionHistoryDTO> data){
        try{
            List<AuctionHistory> allHistories = new ArrayList<>();

            for(NewAuctionHistoryDTO hist : data){
                AuctionHistory history = AuctionHistory.builder()
                        .auction(hist.auction())
                        .amount(hist.amount())
                        .event_type(hist.auctionEventType())
                        .user_id(hist.user())
                        .timeStamp(LocalDateTime.now())
                        .message(hist.message())
                        .build();

                allHistories.add(history);
            }

            historyRepository.saveAll(allHistories);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<AuctionHistory> getAllHistoriesOfAuctions(Auction auction){
        return historyRepository.findAllByauctionNativeQuery(auction.getAuction_id());
    }
    public HashMap<UUID, List<AuctionHistory>> getAllHistoriesOfAuctions(List<Auction> auctions){


        return null;
    }

    public List<FindBidsDTO> getAllBidsInAnAuctionById(UUID id){
        return null;
    }

}
