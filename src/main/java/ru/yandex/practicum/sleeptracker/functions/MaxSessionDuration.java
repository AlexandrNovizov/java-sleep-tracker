package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.results.LongResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

public class MaxSessionDuration extends AnalysisFunction<Long> {
    @Override
    public LongResult apply(List<? extends SleepingSession> sleepingSessions) {
        super.apply(sleepingSessions);
        Optional<Long> minDuration = sleepingSessions.stream()
                .map(sleepingSession -> Duration.between(sleepingSession.getStart(), sleepingSession.getEnd()))
                .map(Duration::toMinutes)
                .max(Long::compare);

        final String details = "Максимальная продолжительность сна (мин)";
        return new LongResult(minDuration.get(), details);
    }
}
