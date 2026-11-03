package dev.eusebiu.nettools.controller;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CsrfController {

    public CsrfToken csrf(CsrfToken csrfToken) {
        return csrfToken;
    }
}
