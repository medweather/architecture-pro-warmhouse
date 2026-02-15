package ru.medweather.api.exception.handler;

public record CustomExceptionResponse(int status, String message, String detailMessage) {}
