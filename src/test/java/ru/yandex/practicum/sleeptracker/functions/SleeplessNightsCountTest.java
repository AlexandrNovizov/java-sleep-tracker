package ru.yandex.practicum.sleeptracker.functions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.SleepQuality;
import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.results.LongResult;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;

public class SleeplessNightsCountTest {

    List<SleepingSession> sessions;

    @BeforeEach
    void init() {
        sessions = new LinkedList<>();
    }

    @Test
    void shouldReturn0IfAllNightSleep() {
        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, Month.OCTOBER, 1, 23, 30),
                LocalDateTime.of(2025, Month.OCTOBER, 2, 8, 0),
                SleepQuality.GOOD
        ));

        LongResult res = new SleeplessNightsCount().apply(sessions);
        assertEquals(0, res.getResult());
    }

    @Test
    void shouldReturn0IfNoSleepBetween0And6Hours() {
        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, Month.OCTOBER, 1, 14, 0),
                LocalDateTime.of(2025, Month.OCTOBER, 1, 15, 0),
                SleepQuality.GOOD
        ));

        LongResult res = new SleeplessNightsCount().apply(sessions);
        assertEquals(0, res.getResult());
    }

    @Test
    void shouldReturn0IfSleepBetween0And6Hours() {
        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, Month.OCTOBER, 1, 1, 0),
                LocalDateTime.of(2025, Month.OCTOBER, 1, 5, 0),
                SleepQuality.GOOD
        ));

        LongResult res = new SleeplessNightsCount().apply(sessions);
        assertEquals(0, res.getResult());
    }

    @Test
    void shouldReturn0IfSleepEndBetween0And6Hours() {
        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, Month.OCTOBER, 1, 23, 0),
                LocalDateTime.of(2025, Month.OCTOBER, 2, 5, 0),
                SleepQuality.GOOD
        ));

        LongResult res = new SleeplessNightsCount().apply(sessions);
        assertEquals(0, res.getResult());
    }

    @Test
    void shouldReturn0IfSleepStartBetween0And6Hours() {
        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, Month.OCTOBER, 1, 1, 0),
                LocalDateTime.of(2025, Month.OCTOBER, 1, 9, 0),
                SleepQuality.GOOD
        ));

        LongResult res = new SleeplessNightsCount().apply(sessions);
        assertEquals(0, res.getResult());
    }

    @Test
    void shouldReturn1IfNoSleepIntersects0And6Hours() {
        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, Month.OCTOBER, 1, 14, 0),
                LocalDateTime.of(2025, Month.OCTOBER, 1, 15, 0),
                SleepQuality.GOOD
        ));

        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, Month.OCTOBER, 2, 7, 0),
                LocalDateTime.of(2025, Month.OCTOBER, 2, 12, 0),
                SleepQuality.GOOD
        ));

        LongResult res = new SleeplessNightsCount().apply(sessions);
        assertEquals(1, res.getResult());
    }

    @Test
    void shouldReturn0IfSeveralSleepsIntersects0And6Hours() {
        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, Month.OCTOBER, 1, 22, 0),
                LocalDateTime.of(2025, Month.OCTOBER, 2, 2, 0),
                SleepQuality.GOOD
        ));

        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, Month.OCTOBER, 2, 2, 30),
                LocalDateTime.of(2025, Month.OCTOBER, 2, 7, 0),
                SleepQuality.GOOD
        ));

        LongResult res = new SleeplessNightsCount().apply(sessions);
        assertEquals(0, res.getResult());
    }

    @Test
    void shouldReturn2For2SleeplessNights() {
        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, Month.OCTOBER, 1, 12, 0),
                LocalDateTime.of(2025, Month.OCTOBER, 1, 18, 0),
                SleepQuality.GOOD
        ));

        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, Month.OCTOBER, 2, 6, 30),
                LocalDateTime.of(2025, Month.OCTOBER, 2, 12, 0),
                SleepQuality.GOOD
        ));

        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, Month.OCTOBER, 3, 6, 30),
                LocalDateTime.of(2025, Month.OCTOBER, 3, 13, 0),
                SleepQuality.GOOD
        ));

        LongResult res = new SleeplessNightsCount().apply(sessions);
        assertEquals(2, res.getResult());
    }
}
