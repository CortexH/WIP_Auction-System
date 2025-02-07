package com.demo.dto.output;

import com.demo.domain.auction.AuctionStatus;
import com.demo.domain.auction.AuctionVisibility;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record AuctionAndHistoryDTO(
        String title,
        String description,
        BigDecimal current_price,
        AuctionStatus status,
        String winner_name,
        LocalDateTime start_time,
        LocalDateTime end_time,
        LocalDateTime creation_date,
        AuctionVisibility visibility,
        List<FindSingleAuctionHistoryDTO> history
) {
}
