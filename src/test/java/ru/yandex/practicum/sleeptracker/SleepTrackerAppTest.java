package ru.yandex.practicum.sleeptracker;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.exceptions.IllegalTimeRangeException;
import ru.yandex.practicum.sleeptracker.exceptions.InvalidStringFormatException;

import java.time.LocalDateTime;
import java.time.Month;

public class SleepTrackerAppTest {

    @Test
    void shouldThrowInvalidStringFormatExceptionForInvalidString() {

        assertThrows(InvalidStringFormatException.class, () -> SleepingSession.fromString("invalid string"));
    }

    @Test
    void shouldReturnIllegalTimeRangeExceptionForStartNotBeforeEnd() {
        assertThrows(IllegalTimeRangeException.class, () -> new SleepingSession(
                LocalDateTime.of(2025, Month.OCTOBER, 1, 0, 0),
                LocalDateTime.of(2025, Month.JUNE, 1, 0, 0),
                SleepQuality.GOOD
        ));
    }
}