package ru.medweather.homemonitoring.exception;

public record CustomExceptionResponse(int status, String message, String detailMessage) {}
