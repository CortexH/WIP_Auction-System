package com.demo.dto.internal;

import com.demo.domain.auction.Auction;
import com.demo.domain.auctionHistory.AuctionEventType;
import com.demo.domain.user.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record NewAuctionHistoryDTO(
        LocalDateTime timeStamp,
        Auction auction,
        BigDecimal amount,
        AuctionEventType auctionEventType,
        User user,
        String message
) {
}
