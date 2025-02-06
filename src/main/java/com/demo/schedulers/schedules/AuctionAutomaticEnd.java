package com.demo.schedulers.schedules;

import com.demo.domain.auction.Auction;
import com.demo.domain.auctionHistory.AuctionEventType;
import com.demo.domain.user.User;
import com.demo.dto.internal.NewAuctionHistoryDTO;
import com.demo.services.AuctionHistoryService;
import com.demo.services.AuctionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuctionAutomaticEnd {

    private final AuctionService auctionService;
    private final AuctionHistoryService auctionHistoryService;

    public void beginProcess(){
        try{
            log.info("AUTOMATIC CLOSE AUCTION PROCESS STARTED AT :: {}", LocalDateTime.now());
            List<Auction> auctions = auctionService.getAuctionsByLessTimeThanDateTime(null);
            if(auctions.isEmpty()){
                log.info("COULD NOT FOUND ANY CLOSEABLE AUCTION");
                endProcess(0);
                return;
            }

            List<UUID> ids = auctions.stream().map(Auction::getAuction_id).toList();
            auctionService.closeAuctionsByListOfUUIDOrSingleUUID(ids);

            List<NewAuctionHistoryDTO> allHistory = new ArrayList<>();

            for(Auction auc : auctions){
                NewAuctionHistoryDTO data = new NewAuctionHistoryDTO(
                            LocalDateTime.now(), auc, auc.getCurrent_price(), AuctionEventType.FINISHED,
                            auc.getWinner_id(), "Auction finished by time."
                        );
                allHistory.add(data);
            }
            auctionHistoryService.createNewHistoryByDataOrListOfData(allHistory);



            endProcess(ids.size());
        } catch (Exception e) {
            log.warn("AN ERROR OCURRED AND THE PROCESS COULD NOT PROCEED :: {}", e.getMessage());
            endProcess(0);
        }
    }

    private void endProcess(Integer num){
        log.info("NUMBER OF AUCTIONS CLOSED :: {}", num);
        log.info("FINISHED PROCESS 'AUTOMATIC CLOSE AUCTIONS'");
    }
}
