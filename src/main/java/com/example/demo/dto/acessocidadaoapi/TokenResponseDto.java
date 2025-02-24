package com.example.demo.dto.acessocidadaoapi;

public record TokenResponseDto(
    String access_token,
    int expires_in,
    String token_type
) {
}
