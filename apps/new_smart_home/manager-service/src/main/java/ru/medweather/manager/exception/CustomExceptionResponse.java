package ru.medweather.manager.exception;

public record CustomExceptionResponse(int status, String message, String detailMessage) {}
