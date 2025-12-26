package ru.yandex.practicum.sleeptracker.exceptions;

public class EmptyListException extends RuntimeException {
    public EmptyListException(String message) {
        super(message);
    }
}
