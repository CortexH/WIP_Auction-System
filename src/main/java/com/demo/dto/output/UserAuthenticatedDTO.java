package com.demo.dto.output;

import java.time.LocalDateTime;

public record UserAuthenticatedDTO(
    LocalDateTime timestamp,
    Integer status,
    String token
) {
}
