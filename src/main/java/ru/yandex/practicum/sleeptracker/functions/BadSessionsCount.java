package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.results.LongResult;
import ru.yandex.practicum.sleeptracker.SleepQuality;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;

public class BadSessionsCount extends AnalysisFunction<Long> {
    @Override
    public LongResult apply(List<? extends SleepingSession> sleepingSessions) {
        super.apply(sleepingSessions);
        long badSessionsCount = sleepingSessions.stream()
                .filter(session -> session.getQuality() == SleepQuality.BAD)
                .count();

        final String details = "Кол-во плохих сессий (шт)";
        return new LongResult(badSessionsCount, details);
    }
}
