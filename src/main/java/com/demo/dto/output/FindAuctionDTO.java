package com.demo.dto.output;

import com.demo.domain.auction.AuctionStatus;

import java.math.BigDecimal;

public record FindAuctionDTO(
    String title,
    BigDecimal price,
    AuctionStatus status,
    String related_user_name
) {
}
