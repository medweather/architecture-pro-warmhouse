package ru.medweather.api.exception.handler;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class CustomExceptionHandler {

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<Object> handleFeignException(FeignException ex) {
        Object response = !StringUtils.isEmpty(ex.contentUTF8()) ? ex.contentUTF8() :
                new CustomExceptionResponse(
                        ex.status(),
                        ex.getMessage(),
                        Optional.ofNullable(ex.getCause()).map(Throwable::getMessage).orElse(null)
                );
        log.error(ex.getMessage());
        return ResponseEntity.status(ex.status()).body(response);
    }
}