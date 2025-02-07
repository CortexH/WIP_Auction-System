package com.demo.dto.output;

import com.demo.domain.auctionHistory.AuctionEventType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record FindSingleAuctionHistoryDTO(
        AuctionEventType eventType,
        BigDecimal amount,
        String message,
        LocalDateTime time,
        String related_user_name
) {
}
