package dev.eusebiu.nettools.exception.handler;

import dev.eusebiu.nettools.config.ApiResponse;
import dev.eusebiu.nettools.exception.InvalidRegistrationException;
import dev.eusebiu.nettools.exception.NicknameAlreadyExistsException;
import dev.eusebiu.nettools.exception.UserAlreadyExistException;
import dev.eusebiu.nettools.exception.WakeOnLanException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WakeOnLanException.class)
    public ResponseEntity<?> handleWakeOnLanException(WakeOnLanException e) {
        log.error("Wake-on-LAN failed", e);
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(status)
                .body(ApiResponse.error(e.getMessage(), status));

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericException(Exception e) {
        log.error("Unexpected error", e);
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(status)
                .body(ApiResponse.error("An unexpected error occurred", status));
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<?> handleUserAlreadyExistsException(UserAlreadyExistException e) {
        log.warn("User already exists: {}", e.getMessage());
        HttpStatus status = HttpStatus.CONFLICT;
        return  ResponseEntity.status(status)
                .body(ApiResponse.error(e.getMessage(), status));
    }

    @ExceptionHandler(InvalidRegistrationException.class)
    public ResponseEntity<?> handleInvalidRegistrationException(InvalidRegistrationException e) {
        log.warn("Invalid registration exception: {}", e.getMessage());
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return  ResponseEntity.status(status)
                .body(ApiResponse.error(e.getMessage(), status));
    }

    @ExceptionHandler(NicknameAlreadyExistsException.class)
    public ResponseEntity<?> handleNicknameAlreadyExistsException(NicknameAlreadyExistsException e) {
        log.warn("Nickname already exists: {}", e.getMessage());
        HttpStatus status = HttpStatus.CONFLICT;
        return  ResponseEntity.status(status)
                .body(ApiResponse.error(e.getMessage(), status));
    }

}
