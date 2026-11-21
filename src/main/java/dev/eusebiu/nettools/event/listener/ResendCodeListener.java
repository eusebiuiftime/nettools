package dev.eusebiu.nettools.event.listener;

import dev.eusebiu.nettools.event.ResendCodeEvent;
import dev.eusebiu.nettools.service.EmailVerificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@RequiredArgsConstructor
public class ResendCodeListener {

    private final EmailVerificationService emailVerificationService;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onResendCode(ResendCodeEvent event) {
        log.info("Verification code resend started: userId={}", event.userId());

        try {
            this.emailVerificationService.sendVerificationCode(event.userId());
            log.info("Verification code resent: userId={}", event.userId());
        } catch (RuntimeException ex) {
            log.error("Verification code resend failed: userId={}", event.userId(), ex);
        }
    }


}
