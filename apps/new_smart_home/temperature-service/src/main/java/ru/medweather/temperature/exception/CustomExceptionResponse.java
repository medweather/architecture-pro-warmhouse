package ru.medweather.temperature.exception;

public record CustomExceptionResponse(int status, String message, String detailMessage) {}
