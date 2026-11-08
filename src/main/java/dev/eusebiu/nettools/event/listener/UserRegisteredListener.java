package dev.eusebiu.nettools.event.listener;

import dev.eusebiu.nettools.event.UserRegisteredEvent;
import dev.eusebiu.nettools.service.EmailVerificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserRegisteredListener {

    private final EmailVerificationService emailVerificationService;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onUserRegistered(UserRegisteredEvent event) {
        log.info("User registration commited: userId={}", event.userId());

        try {
            this.emailVerificationService.sendVerificationCode(event.userId());
            log.info("Email verification sent: userId={}", event.userId());
        } catch (RuntimeException ex) {
            log.error("Email verification failed", ex);
        }

    }
}
