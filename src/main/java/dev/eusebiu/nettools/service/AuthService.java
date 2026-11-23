package dev.eusebiu.nettools.service;

import dev.eusebiu.nettools.dto.LoginRequestDTO;
import dev.eusebiu.nettools.dto.LoginResponseDTO;
import dev.eusebiu.nettools.dto.RegisterRequestDTO;

public interface AuthService {

    void register(RegisterRequestDTO request);

    LoginResponseDTO login(LoginRequestDTO request);
}
