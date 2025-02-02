package com.demo.dto.internal;

import com.demo.domain.auction.Auction;
import com.demo.domain.auctionHistory.EventType;
import com.demo.domain.user.User;

import java.math.BigDecimal;

public record NewHistoryDTO(
        Auction auction,
        BigDecimal amout,
        EventType eventType,
        User user,
        String message
) {
}
