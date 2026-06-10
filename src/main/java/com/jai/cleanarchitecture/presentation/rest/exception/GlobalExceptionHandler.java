package com.jai.cleanarchitecture.presentation.rest.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateKeyException.class)
    public Mono<ResponseEntity<Map<String, Object>>> handleDuplicateKeyException(DuplicateKeyException e, ServerWebExchange exchange) {
        return handleException(e, exchange);
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<Map<String, Object>>> handleGenericException(Exception e, ServerWebExchange exchange) {
        return handleException(e, exchange);
    }

    private Mono<ResponseEntity<Map<String, Object>>> handleException(Exception e, ServerWebExchange exchange) {
        HttpStatus status = determineHttpStatus(e);
        String error = status.getReasonPhrase();
        
        log.error("{} exception: {}", error, e.getMessage(), e);
        
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("status", status.value());
        errorResponse.put("error", error);
        errorResponse.put("message", e.getMessage());
        errorResponse.put("path", exchange.getRequest().getPath().value());
        
        return Mono.just(ResponseEntity.status(status).body(errorResponse));
    }

    private HttpStatus determineHttpStatus(Exception e) {
        if (e instanceof DuplicateKeyException) {
            return HttpStatus.CONFLICT;
        } else if (e instanceof IllegalArgumentException) {
            return HttpStatus.BAD_REQUEST;
        } else if (e instanceof NoSuchElementException) {
            return HttpStatus.NOT_FOUND;
        } else {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
