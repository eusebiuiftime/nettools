package dev.eusebiu.nettools.service;

import dev.eusebiu.nettools.dto.RegisterRequestDTO;
import jakarta.servlet.Registration;

public interface AuthService {
    void register(RegisterRequestDTO request);
}
