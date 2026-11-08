package dev.eusebiu.nettools.client.impl;

import dev.eusebiu.nettools.client.EmailClient;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailClientImpl implements EmailClient {

    private final JavaMailSender javaMailSender;

    @Value("${nettools.mail.from}")
    private String from;

    @Override
    public void send(String to, String subject, String html) {

       try {
           MimeMessage mimeMessage = javaMailSender.createMimeMessage();
           MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

           helper.setFrom(from);
           helper.setTo(to);
           helper.setSubject(subject);
           helper.setText(html, true);

           this.javaMailSender.send(mimeMessage);

       } catch (MessagingException e) {
           throw new IllegalStateException("Failed to send email", e);
       }
    }

}
