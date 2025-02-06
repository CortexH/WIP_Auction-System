package com.demo.dto.output;

import java.math.BigDecimal;

public record FindBidsDTO(
        String time,
        BigDecimal amount,
        String username
) {
}
