package ru.yandex.practicum.sleeptracker.exceptions;

public class InvalidStringFormatException extends RuntimeException {
    public InvalidStringFormatException(String message) {
        super(message);
    }
}
