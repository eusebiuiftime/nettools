package dev.eusebiu.nettools.dto;

public record LoginResponseDTO(
        String accessToken,
        String tokenType
) {
}
