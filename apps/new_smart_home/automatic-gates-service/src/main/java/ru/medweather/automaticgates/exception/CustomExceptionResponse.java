package ru.medweather.automaticgates.exception;

public record CustomExceptionResponse(int status, String message, String detailMessage) {}
