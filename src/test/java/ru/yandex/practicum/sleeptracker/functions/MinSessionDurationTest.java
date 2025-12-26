package ru.yandex.practicum.sleeptracker.functions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.SleepQuality;
import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.exceptions.EmptyListException;
import ru.yandex.practicum.sleeptracker.results.LongResult;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MinSessionDurationTest {

    List<SleepingSession> sessions;

    @BeforeEach
    void init() {
        sessions = new LinkedList<>();
    }

    @Test
    void shouldReturnMaxDuration() {
        int year = 2000;
        Month month = Month.JUNE;
        int day = 3;
        SleepQuality quality = SleepQuality.GOOD;

        SleepingSession fiveMinutesSession = new SleepingSession(
                LocalDateTime.of(year, month, day, 13, 0),
                LocalDateTime.of(year, month, day, 13, 5),
                quality);

        SleepingSession tenMinutesSession = new SleepingSession(
                LocalDateTime.of(year, month, day, 15, 0),
                LocalDateTime.of(year, month, day, 15, 10),
                quality
        );

        sessions.add(fiveMinutesSession);
        sessions.add(tenMinutesSession);

        LongResult res = new MinSessionDuration().apply(sessions);

        assertEquals(5, res.getResult());
    }

    @Test
    void shouldReturn10ForOnlyDuration10() {
        int year = 2000;
        Month month = Month.JUNE;
        int day = 3;
        SleepQuality quality = SleepQuality.GOOD;

        SleepingSession tenMinutesSession = new SleepingSession(
                LocalDateTime.of(year, month, day, 15, 0),
                LocalDateTime.of(year, month, day, 15, 10),
                quality
        );

        sessions.add(tenMinutesSession);
        LongResult res = new MinSessionDuration().apply(sessions);

        assertEquals(10, res.getResult());
    }

    @Test
    void shouldThrowEmptyListException() {

        assertThrows(EmptyListException.class, () -> new MinSessionDuration().apply(sessions));
    }
}
