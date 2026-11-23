package dev.eusebiu.nettools.dto;

public record VerifyEmailRequestDTO(
        String email,
        String code
) {
}
