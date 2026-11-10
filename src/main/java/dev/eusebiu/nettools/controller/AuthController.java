package dev.eusebiu.nettools.controller;

import dev.eusebiu.nettools.config.ApiResponse;
import dev.eusebiu.nettools.dto.RegisterRequestDTO;
import dev.eusebiu.nettools.dto.VerifyEmailRequestDTO;
import dev.eusebiu.nettools.service.AuthService;
import dev.eusebiu.nettools.service.EmailVerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final EmailVerificationService emailVerificationService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO request) {
        this.authService.register(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Register successfully. Check your email address."));

    }

    @PostMapping("/verify-email")
    public ResponseEntity<?> verifyEmail(@RequestBody VerifyEmailRequestDTO request) {
        this.emailVerificationService.verifyCode(
                request.email(),
                request.code()
        );
        return ResponseEntity.ok(
                ApiResponse.ok("Verify email successfully.")
        );

    }
}
