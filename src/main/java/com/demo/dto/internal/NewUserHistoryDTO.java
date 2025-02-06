package com.demo.dto.internal;

import com.demo.domain.auction.Auction;
import com.demo.domain.user.User;
import com.demo.domain.userHistory.UserEventType;

import java.math.BigDecimal;

public record NewUserHistoryDTO(
        User user,
        Auction auction,
        BigDecimal amount,
        UserEventType userEventType,
        String message

) {
}
