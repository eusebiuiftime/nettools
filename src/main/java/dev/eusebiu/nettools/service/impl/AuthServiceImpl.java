package dev.eusebiu.nettools.service.impl;

import dev.eusebiu.nettools.dto.RegisterRequestDTO;
import dev.eusebiu.nettools.entity.Role;
import dev.eusebiu.nettools.entity.User;
import dev.eusebiu.nettools.enums.RoleEnum;
import dev.eusebiu.nettools.event.UserRegisteredEvent;
import dev.eusebiu.nettools.exception.InvalidRegistrationException;
import dev.eusebiu.nettools.exception.NicknameAlreadyExistsException;
import dev.eusebiu.nettools.exception.UserAlreadyExistException;
import dev.eusebiu.nettools.repository.RoleRepository;
import dev.eusebiu.nettools.repository.UserRepository;
import dev.eusebiu.nettools.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    @Transactional
    public void register(RegisterRequestDTO request) {
        log.info("User registration started");
        RegisterRequestDTO requestNormalized = validateAndNormalize(request);

        if (this.userRepository.existsByEmail(requestNormalized.email())) {
            throw new UserAlreadyExistException("User with email %s already exist"
                    .formatted(requestNormalized.email())
            );
        }

        if (this.userRepository.existsByNicknameIgnoreCase(requestNormalized.nickname())) {
            throw new NicknameAlreadyExistsException("Nickname %s already registered"
                    .formatted(requestNormalized.nickname())
            );
        }

        Role role = roleRepository.findByName(RoleEnum.ROLE_USER)
                .orElseThrow(() -> new IllegalStateException("ROLE_USER not found"));

        String password = passwordEncoder.encode(requestNormalized.password());

        User user = User.builder()
                .email(requestNormalized.email())
                .nickname(requestNormalized.nickname())
                .passwordHash(password)
                .enabled(true)
                .emailVerified(false)
                .build();
        user.getRoles().add(role);
        this.userRepository.save(user);

        log.debug("User persisted, awaiting commit: userId={}", user.getId());

        this.applicationEventPublisher.publishEvent(
                new UserRegisteredEvent(user.getId())
        );
    }

    private RegisterRequestDTO validateAndNormalize(RegisterRequestDTO request) {
        if (request == null
                || request.email() == null || request.email().isBlank()
                || request.nickname() == null || request.nickname().isBlank()
                || request.password() == null || request.password().isBlank()) {
            throw new InvalidRegistrationException("All fields are required");
        }

        String email = request.email().trim().toLowerCase(Locale.ROOT);
        String nickname = request.nickname().trim();

        if (!email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
            throw new InvalidRegistrationException("Invalid email format");
        }

        if (email.length() > 255 || nickname.length() > 50) {
            throw new InvalidRegistrationException("Email or nickname is too long");
        }

        if (request.password().length() < 8) {
            throw new InvalidRegistrationException("Password must contain at least 8 characters");
        }
        return new RegisterRequestDTO(
                nickname,
                email,
                request.password()
        );
    }
}
