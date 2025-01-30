package com.demo.dto.output;

import java.time.LocalDateTime;
import java.util.HashMap;

public record GenericErrorDTO(
        LocalDateTime timestamp,
        Integer status,
        String error,
        String message
) {
}
