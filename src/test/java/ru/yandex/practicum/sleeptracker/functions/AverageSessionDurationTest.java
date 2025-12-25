package ru.yandex.practicum.sleeptracker.functions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.SleepQuality;
import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.exceptions.EmptyListException;
import ru.yandex.practicum.sleeptracker.results.DoubleResult;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class AverageSessionDurationTest {

    List<SleepingSession> sessions;

    @BeforeEach
    void init() {
        sessions = new LinkedList<>();
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
        DoubleResult res = new AverageSessionDuration().apply(sessions);

        assertEquals(10, res.getResult());
    }

    @Test
    void shouldReturn15ForDurations10And20() {
        int year = 2000;
        Month month = Month.JUNE;
        int day = 3;
        SleepQuality quality = SleepQuality.GOOD;

        SleepingSession twelveMinutesSession = new SleepingSession(
                LocalDateTime.of(year, month, day, 13, 3),
                LocalDateTime.of(year, month, day, 13, 23),
                quality);

        SleepingSession tenMinutesSession = new SleepingSession(
                LocalDateTime.of(year, month, day, 15, 0),
                LocalDateTime.of(year, month, day, 15, 10),
                quality
        );

        sessions.add(twelveMinutesSession);
        sessions.add(tenMinutesSession);
        DoubleResult res = new AverageSessionDuration().apply(sessions);

        assertEquals(15, res.getResult());
    }

    @Test
    void shouldThrowEmptyListException() {

        assertThrows(EmptyListException.class, () -> new MaxSessionDuration().apply(sessions));
    }
}
