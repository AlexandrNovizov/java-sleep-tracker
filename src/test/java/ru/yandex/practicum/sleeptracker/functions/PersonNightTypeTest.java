package ru.yandex.practicum.sleeptracker.functions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.NightType;
import ru.yandex.practicum.sleeptracker.SleepQuality;
import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.results.NightTypeResult;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonNightTypeTest {

    List<SleepingSession> sessions;

    @BeforeEach
    void init() {
        sessions = new LinkedList<>();
    }

    @Test
    void shouldReturnNightOwlIfMajorityOfNightsIsNightOwl() {
        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, Month.OCTOBER, 1, 23, 5),
                LocalDateTime.of(2025, Month.OCTOBER, 2, 10, 5),
                SleepQuality.GOOD
        ));
        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, Month.OCTOBER, 2, 21, 5),
                LocalDateTime.of(2025, Month.OCTOBER, 3, 6, 5),
                SleepQuality.GOOD
        ));
        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, Month.OCTOBER, 4, 0, 14),
                LocalDateTime.of(2025, Month.OCTOBER, 4, 11, 3),
                SleepQuality.GOOD
        ));

        NightTypeResult res = new PersonNightType().apply(sessions);

        assertEquals(NightType.NIGHT_OWL, res.getResult());
    }

    @Test
    void shouldReturnEarlyBirdIfMajorityOfNightsIsEarlyBird() {
        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, Month.OCTOBER, 1, 21, 5),
                LocalDateTime.of(2025, Month.OCTOBER, 2, 6, 5),
                SleepQuality.GOOD
        ));
        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, Month.OCTOBER, 2, 20, 57),
                LocalDateTime.of(2025, Month.OCTOBER, 3, 5, 45),
                SleepQuality.GOOD
        ));
        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, Month.OCTOBER, 4, 0, 14),
                LocalDateTime.of(2025, Month.OCTOBER, 4, 11, 3),
                SleepQuality.GOOD
        ));

        NightTypeResult res = new PersonNightType().apply(sessions);

        assertEquals(NightType.EARLY_BIRD, res.getResult());
    }

    @Test
    void shouldReturnHummingbirdIfMajorityOfNightsIsHummingBird() {
        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, Month.OCTOBER, 1, 23, 5),
                LocalDateTime.of(2025, Month.OCTOBER, 2, 6, 5),
                SleepQuality.GOOD
        ));
        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, Month.OCTOBER, 2, 23, 57),
                LocalDateTime.of(2025, Month.OCTOBER, 3, 5, 45),
                SleepQuality.GOOD
        ));
        sessions.add(new SleepingSession(
                LocalDateTime.of(2025, Month.OCTOBER, 4, 0, 14),
                LocalDateTime.of(2025, Month.OCTOBER, 4, 11, 3),
                SleepQuality.GOOD
        ));

        NightTypeResult res = new PersonNightType().apply(sessions);

        assertEquals(NightType.HUMMINGBIRD, res.getResult());
    }
}