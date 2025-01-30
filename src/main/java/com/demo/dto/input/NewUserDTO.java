package com.demo.dto.input;

public record NewUserDTO(
    String username,
    String email,
    String password
) {
}
