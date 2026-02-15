package ru.medweather.payment.exception;

public record CustomExceptionResponse(int status, String message, String detailMessage) {}
