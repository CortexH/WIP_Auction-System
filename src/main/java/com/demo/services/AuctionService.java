package com.demo.services;

import com.demo.domain.auction.Auction;
import com.demo.domain.auction.AuctionStatus;
import com.demo.domain.auctionHistory.AuctionEventType;
import com.demo.domain.user.User;
import com.demo.dto.input.NewAuctionDTO;
import com.demo.dto.internal.NewAuctionHistoryDTO;
import com.demo.dto.output.GenericSuccessDTO;
import com.demo.repositories.AuctionRepository;
import com.demo.security.CookieUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class AuctionService {

    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private AuctionHistoryService historyService;

    @Autowired
    private UserService userService;

    public GenericSuccessDTO createNewAuction(NewAuctionDTO data, HttpServletRequest request){
            Cookie cookie = CookieUtils.getCookieFromArray(request.getCookies());
            User user = userService.getUserByToken(cookie.getValue());

            Auction auction = Auction.builder()
                    .creation_date(LocalDateTime.now())
                    .description(data.description())
                    .title(data.title())
                    .start_time(data.start_time())
                    .end_time(data.start_time().plusHours(3))
                    .status(AuctionStatus.SCHEDULED)
                    .starting_price(data.starting_price())
                    .owner_id(user)
                    .winner_id(null)
                    .build();


            auctionRepository.save(auction);

            NewAuctionHistoryDTO historyDTO = new NewAuctionHistoryDTO(auction, null, AuctionEventType.CREATED, null, "Created new auction");

            historyService.createNewHistoryByDataOrListOfData(historyDTO);

            return new GenericSuccessDTO(LocalDateTime.now(), 200, "Auction created");

    }


    public List<Auction> getAuctionsByLessTimeThanDateTime(LocalDateTime time){
        //LocalDateTime ltime = (time != null) ? time : LocalDateTime.now();
        return auctionRepository.findAllByLessDateTimeThanParam(
                (time != null) ? time : LocalDateTime.now()
        );
    }

    public void closeAuctionsByListOfUUIDOrSingleUUID(List<UUID> list){
        try{
            auctionRepository.closeAuctionsByUUIDList(list);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void closeAuctionsByListOfUUIDOrSingleUUID(UUID id){
        try{
            List<UUID> list = new ArrayList<>();
            list.add(id);
            auctionRepository.closeAuctionsByUUIDList(list);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Auction findAuctionById(UUID uuid){
        return auctionRepository.findById(uuid)
                .orElseThrow(() -> new NoSuchElementException("Could not find auctions with specified id"));
    }

    public List<Auction> getAllByIdsList(List<UUID> ids){
        return auctionRepository.findAllById(ids);
    }

}



