package com.demo.dto.output;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record FindBidsDTO(
        String time,
        BigDecimal amout,
        String username
) {
}
