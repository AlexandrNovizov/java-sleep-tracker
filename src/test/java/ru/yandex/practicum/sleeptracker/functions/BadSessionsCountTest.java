package ru.yandex.practicum.sleeptracker.functions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

public class BadSessionsCountTest {

    List<SleepingSession> sessions;

    @BeforeEach
    void init() {
        sessions = new LinkedList<>();
    }

    @Test
    void shouldReturn0IfNoBadSessions() {
        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, Month.OCTOBER, 1, 23, 15),
                LocalDateTime.of(2025, Month.OCTOBER, 2, 7, 30),
                SleepQuality.GOOD
        ));
        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, Month.OCTOBER, 2, 23, 50),
                LocalDateTime.of(2025, Month.OCTOBER, 3, 6, 40),
                SleepQuality.NORMAL
        ));

        LongResult res = new BadSessionsCount().apply(sessions);
        assertEquals(0, res.getResult());
    }

    @Test
    void shouldReturn2If2BadSessions() {
        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, Month.OCTOBER, 1, 23, 15),
                LocalDateTime.of(2025, Month.OCTOBER, 2, 7, 30),
                SleepQuality.GOOD
        ));
        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, Month.OCTOBER, 2, 23, 50),
                LocalDateTime.of(2025, Month.OCTOBER, 3, 6, 40),
                SleepQuality.NORMAL
        ));
        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, Month.OCTOBER, 3, 23, 40),
                LocalDateTime.of(2025, Month.OCTOBER, 4, 8, 0),
                SleepQuality.BAD
        ));

        BadSessionsCount fn = new BadSessionsCount();
        LongResult res = fn.apply(sessions);
        assertEquals(1, res.getResult());

        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, Month.OCTOBER, 11, 23, 10),
                LocalDateTime.of(2025, Month.OCTOBER, 12, 7, 0),
                SleepQuality.BAD
        ));
        res = fn.apply(sessions);
        assertEquals(2, res.getResult());
    }

    @Test
    void shouldThrowEmptyListException() {

        assertThrows(EmptyListException.class, () -> new BadSessionsCount().apply(sessions));
    }
}
