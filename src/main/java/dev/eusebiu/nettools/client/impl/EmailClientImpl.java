package dev.eusebiu.nettools.client.impl;

import dev.eusebiu.nettools.client.EmailClient;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailClientImpl implements EmailClient {

    private final JavaMailSender javaMailSender;

    @Value("${nettools.mail.from}")
    private String from;

    @Override
    public void send(String to, String subject, String body) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);
    }

}
