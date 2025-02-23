package com.demo.dto.input;

import com.demo.domain.auction.AuctionVisibility;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record NewAuctionDTO(
    String title,
    String description,
    BigDecimal starting_price,
    LocalDateTime start_time,
    AuctionVisibility visibility
) { }
