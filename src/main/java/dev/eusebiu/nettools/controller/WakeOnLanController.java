package dev.eusebiu.nettools.controller;

import dev.eusebiu.nettools.service.WakeOnLanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wol")
@RequiredArgsConstructor
public class WakeOnLanController {

    private final WakeOnLanService wakeOnLanService;

    @PostMapping
    public ResponseEntity<?> wake() {
        this.wakeOnLanService.wake();
        return ResponseEntity.noContent().build();
    }

}
