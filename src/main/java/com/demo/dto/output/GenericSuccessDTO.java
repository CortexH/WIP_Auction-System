package com.demo.dto.output;

import java.time.LocalDateTime;

public record GenericSuccessDTO(
    LocalDateTime timestamp,
    Integer status,
    String message
) {
}
