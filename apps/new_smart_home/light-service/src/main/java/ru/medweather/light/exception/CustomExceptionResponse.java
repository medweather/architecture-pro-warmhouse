package ru.medweather.light.exception;

public record CustomExceptionResponse(int status, String message, String detailMessage) {}
