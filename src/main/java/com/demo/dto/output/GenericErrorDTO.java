package com.demo.dto.output;

import java.time.LocalDateTime;
import java.util.HashMap;

public record GenericErrorDTO(
        String timestamp,
        Integer status,
        String error,
        String message
) {
}
