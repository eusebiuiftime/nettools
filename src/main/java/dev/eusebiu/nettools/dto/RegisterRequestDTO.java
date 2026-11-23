package dev.eusebiu.nettools.dto;

public record RegisterRequestDTO(
        String nickname,
        String email,
        String password
) {
}
