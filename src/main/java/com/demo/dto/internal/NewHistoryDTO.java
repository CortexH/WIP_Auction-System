package com.demo.dto.internal;

import com.demo.domain.auction.Auction;
import com.demo.domain.auctionHistory.AuctionEventType;
import com.demo.domain.user.User;

import java.math.BigDecimal;

public record NewHistoryDTO(
        Auction auction,
        BigDecimal amount,
        AuctionEventType eventType,
        User user,
        String message
) {
}
