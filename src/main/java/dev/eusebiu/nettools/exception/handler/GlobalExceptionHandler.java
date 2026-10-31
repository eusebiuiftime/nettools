package dev.eusebiu.nettools.exception.handler;

import dev.eusebiu.nettools.exception.WakeOnLanException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WakeOnLanException.class)
    public ResponseEntity<?> handleWakeOnLanException(WakeOnLanException e) {
        log.error("Wake-on-LAN failed", e);

        return ResponseEntity.
    }

}
