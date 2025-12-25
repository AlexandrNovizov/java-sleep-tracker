package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.results.LongResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.exceptions.NotNightSessionException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Objects;

public class SleeplessNightsCount extends SleepAnalysisFunction<Long> {
    @Override
    public LongResult apply(List<? extends SleepingSession> sleepingSessions) {
        super.apply(sleepingSessions);
        long sleepNights = sleepingSessions.stream()
                .filter(SleepingSession::isNightSession)
                .map(Night::fromSession)
                .distinct()
                .count();

        LocalDateTime startPeriod = sleepingSessions.getFirst().getStart();
        LocalDateTime endPeriod = sleepingSessions.getLast().getEnd();

        long allNights = Period.between(startPeriod.toLocalDate(), endPeriod.toLocalDate()).getDays();

        if (startPeriod.toLocalDate().equals(endPeriod.toLocalDate()) && sleepNights != 0) {
            allNights = sleepNights;
        }

        if (startPeriod.getHour() < 12) {
            if (startPeriod.getHour() < 6) {
                sleepNights++;
            }
            allNights++;
        }

        final String details = "Кол-во бессонных ночей (шт)";
        return new LongResult(Math.abs(allNights - sleepNights), details);
    }

    static class Night {
        final LocalDate from;
        final LocalDate to;

        private Night(LocalDate from, LocalDate to) {
            this.from = from;
            this.to = to;
        }

        public static Night fromSession(SleepingSession session) {
            if (!session.isNightSession()) {
                throw new NotNightSessionException("Сессия " + session + " не ночная");
            }

            LocalDate start = session.getStart().toLocalDate();
            LocalDate end = session.getEnd().toLocalDate();

            if (start.isBefore(end)) {
                return new Night(start, end);
            }

            return new Night(start.minusDays(1), end);
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Night night = (Night) o;
            return Objects.equals(from, night.from) && Objects.equals(to, night.to);
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }
    }
}
