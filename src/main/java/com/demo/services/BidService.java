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
import com.demo.security.CookieUtils;
import com.demo.security.JwtUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class BidService {

    @Autowired
    private BidRepository bidRepository;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserService userService;
    @Autowired
    private AuctionService auctionService;
    @Autowired
    private AuctionHistoryService auctionHistoryService;

    public GenericSuccessDTO bidAuction(HttpServletRequest request, NewBidDTO data){
        Cookie cookie = CookieUtils.getCookieFromArray(request.getCookies());
        String token = cookie.getValue();

        User user = userService.getUserByToken(token);
        Auction auction = auctionService.findAuctionById(data.auction_id());

        if(user.getPurse().compareTo(data.amout()) < 0) throw new ConflictException("Not enough cash in purse to realize this action!");
        if(data.amout().compareTo(auction.getStarting_price()) < 0) throw new ConflictException("You can't insert less cash than the starting price!");

        Bid bid = Bid.builder()
                .auction_id(auction)
                .user_id(user)
                .timeStamp(LocalDateTime.now())
                .amout(data.amout())
                .build();

        bidRepository.save(bid);

        NewAuctionHistoryDTO hist = new NewAuctionHistoryDTO(
                auction, bid.getAmout(), AuctionEventType.BID_GIVEN,
                user, ("User " + user.getUsername() + " bidded")
        );

        auctionHistoryService.createNewHistoryByDataOrListOfData(hist);

        return null;
    }

    public List<FindBidsDTO> getAllBidsInAnAuction(UUID id){
        return null;
    }

}
