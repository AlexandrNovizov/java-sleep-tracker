package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.results.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.exceptions.EmptyListException;

import java.util.List;
import java.util.function.Function;

public abstract class AnalysisFunction<T> implements Function<List<? extends SleepingSession>, SleepAnalysisResult<T>> {
    @Override
    public SleepAnalysisResult<T> apply(List<? extends SleepingSession> sleepingSessions) {
        if (sleepingSessions.isEmpty()) {
            throw new EmptyListException("Коллекция сессий пуста!");
        }
        return null;
    }
}
