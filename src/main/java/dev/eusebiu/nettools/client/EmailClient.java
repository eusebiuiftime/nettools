package dev.eusebiu.nettools.client;

public interface EmailClient {
    void send(String to, String subject, String body);
}
