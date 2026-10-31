package dev.eusebiu.nettools.config;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ApiResponse<T>(
        int status,
        String StatusCode,
        T data,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime timestamp
) {

    public static <T> ApiResponse<T> ok(T data, HttpStatus status) {
        return new ApiResponse<>(
                status.value(),
                status.getReasonPhrase(),
                data,
                LocalDateTime.now()
        );
    }

    public static <T>  ApiResponse<T> ok(T data) {
        return ok(data, HttpStatus.OK);
    }

    public static <T>  ApiResponse<T> error(T data, HttpStatus status) {
        return new ApiResponse<>(
                status.value(),
                status.getReasonPhrase(),
                data,
                LocalDateTime.now()
        );
    }

    public static <T>  ApiResponse<T> error(T data) {
        return error(data, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
