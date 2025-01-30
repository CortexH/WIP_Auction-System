package com.demo.dto.input;

import java.math.BigDecimal;

public record NewAuctionDTO(
    String title,
    String description,
    BigDecimal starting_price,
    String owner_id
) {
}
