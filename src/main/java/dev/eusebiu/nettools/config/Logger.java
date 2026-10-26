package dev.eusebiu.nettools.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class Logger extends OncePerRequestFilter {

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getRequestURI().startsWith("/actuator");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                   HttpServletResponse response,
                                   FilterChain filterChain
    ) throws ServletException, IOException {

        String traceId = UUID.randomUUID().toString();
        long start = System.nanoTime();

        MDC.put("traceId", traceId);
        response.setHeader("X-Trace-Id", traceId);

        try {
            log.info("Request: [{}] {}",
                    request.getMethod(),
                    request.getRequestURI());

            filterChain.doFilter(request, response);

        } finally {
            long duration = TimeUnit.NANOSECONDS.toMillis(
                    System.nanoTime() - start
            );

            log.info("Response: [{}] completed in {}ms",
                    response.getStatus(),
                    duration);

            MDC.remove("traceId");
        }
    }
}
