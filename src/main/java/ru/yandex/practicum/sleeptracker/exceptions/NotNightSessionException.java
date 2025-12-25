package ru.yandex.practicum.sleeptracker.exceptions;

public class NotNightSessionException extends RuntimeException {
    public NotNightSessionException(String message) {
        super(message);
    }
}
