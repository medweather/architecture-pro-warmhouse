package ru.medweather.homemonitoring.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class CustomExceptionHandler {

    private static ResponseEntity<CustomExceptionResponse> buildResponse(HttpStatus status, Exception ex) {
        return ResponseEntity.status(status)
                .body(new CustomExceptionResponse(
                                status.value(),
                                ex.getMessage(),
                                Optional.ofNullable(ex.getCause()).map(Throwable::getMessage).orElse(null)
                        )
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomExceptionResponse> handleOtherException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }
}