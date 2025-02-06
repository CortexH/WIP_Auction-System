package com.demo.dto.input;

import java.math.BigDecimal;
import java.util.UUID;

public record NewBidDTO(
    UUID auction_id,
    BigDecimal amount
) {
}
