package com.demo.services;

import com.demo.domain.auction.Auction;
import com.demo.domain.auctionHistory.AuctionEventType;
import com.demo.domain.bid.Bid;
import com.demo.domain.user.User;
import com.demo.dto.input.NewBidDTO;
import com.demo.dto.internal.NewAuctionHistoryDTO;
import com.demo.dto.output.FindBidsDTO;
import com.demo.dto.output.GenericSuccessDTO;
import com.demo.exceptions.ConflictException;
import com.demo.repositories.BidRepository;
import com.demo.utils.CookieUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BidService {

    private final BidRepository bidRepository;
    private final UserService userService;
    private final AuctionService auctionService;
    private final AuctionHistoryService auctionHistoryService;

    public GenericSuccessDTO bidAuction(HttpServletRequest request, NewBidDTO data){
        User user = userService.findUserByHttpServletRequest(request);
        Auction auction = auctionService.findAuctionById(data.auction_id());

        if(user.getPurse().compareTo(data.amount()) < 0) throw new ConflictException("Not enough cash in purse to realize this action!");
        if(data.amount().compareTo(auction.getStarting_price()) < 0) throw new ConflictException("You can't insert less cash than the starting price!");

        Bid bid = Bid.builder()
                .auction_id(auction)
                .user_id(user)
                .timeStamp(LocalDateTime.now())
                .amount(data.amount())
                .build();

        bidRepository.save(bid);

        NewAuctionHistoryDTO hist = new NewAuctionHistoryDTO(
                LocalDateTime.now(), auction, bid.getAmount(),
                AuctionEventType.BID_GIVEN, user,
                ("User " + user.getUsername() + " bidded")
        );

        auctionHistoryService.createNewHistoryByDataOrListOfData(hist);

        return null;
    }

    public List<FindBidsDTO> getAllBidsInAnAuction(UUID id){
        return null;
    }

}
