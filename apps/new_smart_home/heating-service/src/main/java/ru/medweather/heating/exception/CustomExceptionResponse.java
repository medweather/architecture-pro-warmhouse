package ru.medweather.heating.exception;

public record CustomExceptionResponse(int status, String message, String detailMessage) {}
