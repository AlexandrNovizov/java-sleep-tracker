package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.results.DoubleResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.Duration;
import java.util.List;
import java.util.OptionalDouble;

public class AverageSessionDuration extends SleepAnalysisFunction<Double> {
    @Override
    public DoubleResult apply(List<? extends SleepingSession> sleepingSessions) {
        super.apply(sleepingSessions);
        OptionalDouble minDuration = sleepingSessions.stream()
                .map(sleepingSession -> Duration.between(sleepingSession.getStart(), sleepingSession.getEnd()))
                .map(Duration::toMinutes)
                .mapToLong(Long::longValue)
                .average();

        final String details = "Средняя продолжительность сна (мин)";
        return new DoubleResult(minDuration.getAsDouble(), details);
    }
}
