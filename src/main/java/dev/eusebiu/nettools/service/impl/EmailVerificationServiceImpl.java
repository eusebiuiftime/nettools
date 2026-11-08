package dev.eusebiu.nettools.service.impl;

import dev.eusebiu.nettools.client.EmailClient;
import dev.eusebiu.nettools.entity.EmailVerificationToken;
import dev.eusebiu.nettools.entity.User;
import dev.eusebiu.nettools.exception.UserNotFoundException;
import dev.eusebiu.nettools.repository.EmailVerificationTokenRepository;
import dev.eusebiu.nettools.repository.UserRepository;
import dev.eusebiu.nettools.service.EmailVerificationService;


import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HexFormat;

@Service
@RequiredArgsConstructor
public class EmailVerificationServiceImpl  implements EmailVerificationService {

    @Value("classpath:templates/email-verification.html")
    private Resource template;

    private final EmailVerificationTokenRepository emailVerificationTokenRepository;
    private final UserRepository userRepository;
    private final EmailClient emailClient;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void sendVerificationCode(Long userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        int expiration = 10;
        String code = generateCode();
        String tokenHash = hashCode(code);

        EmailVerificationToken token = EmailVerificationToken.builder()
                .user(user)
                .tokenHash(tokenHash)
                .expiresAt(Instant.now().plus(expiration, ChronoUnit.MINUTES))
                .build();

        this.emailVerificationTokenRepository.save(token);

        String html = buildVerificationEmail(code, expiration);

        this.emailClient.send(
                user.getEmail(),
                "%s è il tuo codice di verifica NetTools"
                        .formatted(code),
                html
        );
    }

    private String generateCode() {
        SecureRandom random = new SecureRandom();
        int code = random.nextInt(1_000_000);
        return "%06d".formatted(code);
    }

    private String hashCode(String code) {
        try {
            MessageDigest algorithm = MessageDigest.getInstance("SHA-256");

            byte[] hash = algorithm.digest(
                        code.getBytes(StandardCharsets.UTF_8)
            );

            return HexFormat.of().formatHex(hash);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found", e);
        }
    }

    private String buildVerificationEmail(String code, int minutes) {
        try {
            String html = template.getContentAsString(StandardCharsets.UTF_8);

            return html
                    .replace("{{CODE}}", code)
                    .replace("{{minutes}}", String.valueOf(minutes));

        } catch (IOException e) {
            throw new IllegalStateException("Failed to load email template", e);
        }
    }
}
