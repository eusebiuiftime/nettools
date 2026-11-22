package dev.eusebiu.nettools.controller;

import dev.eusebiu.nettools.service.WakeOnLanService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wol")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class WakeOnLanController {

    private final WakeOnLanService wakeOnLanService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/wake")
    public ResponseEntity<?> wake() {
        this.wakeOnLanService.wake();
        return ResponseEntity.noContent().build();
    }

}
