package dev.eusebiu.nettools.event.listener;

import dev.eusebiu.nettools.event.UserRegisteredEvent;
import dev.eusebiu.nettools.service.EmailVerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class UserRegisteredListener {

    private final EmailVerificationService emailVerificationService;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onUserRegistered(UserRegisteredEvent event) {
        this.emailVerificationService.sendVerificationCode(event.userId());
    }
}
