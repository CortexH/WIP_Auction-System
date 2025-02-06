package com.demo.dto.output;

import java.time.LocalDateTime;
import java.util.UUID;

public record AuctionCreatedDTO(
        LocalDateTime timeStamp,
        Integer status,
        String message,
        UUID auction_registry
) {
}
