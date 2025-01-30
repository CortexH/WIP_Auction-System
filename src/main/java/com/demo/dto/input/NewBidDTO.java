package com.demo.dto.input;

import java.math.BigDecimal;

public record NewBidDTO(
    String auction_id,
    String user_id,
    BigDecimal amout
) {
}
