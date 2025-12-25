package ru.yandex.practicum.sleeptracker.exceptions;

public class IllegalTimeRangeException extends RuntimeException {
    public IllegalTimeRangeException(String message) {
        super(message);
    }
}
