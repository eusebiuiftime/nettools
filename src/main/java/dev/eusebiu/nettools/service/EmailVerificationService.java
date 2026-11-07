package dev.eusebiu.nettools.service;

import dev.eusebiu.nettools.entity.User;

public interface EmailVerificationService {

    void sendVerificationCode(User user);
}
