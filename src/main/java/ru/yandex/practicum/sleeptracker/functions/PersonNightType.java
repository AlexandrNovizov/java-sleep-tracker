package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.NightType;
import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.results.NightTypeResult;

import java.util.List;

public class PersonNightType extends AnalysisFunction<NightType> {
    @Override
    public NightTypeResult apply(List<? extends SleepingSession> sleepingSessions) {
        super.apply(sleepingSessions);

        long hummingBirdCount = sleepingSessions.stream()
                .filter(SleepingSession::isNightSession)
                .map(SleepingSession::getNightType)
                .filter(nightType -> nightType == NightType.HUMMINGBIRD)
                .count();

        long nightOwlCount = sleepingSessions.stream()
                .filter(SleepingSession::isNightSession)
                .map(SleepingSession::getNightType)
                .filter(nightType -> nightType == NightType.NIGHT_OWL)
                .count();

        long earlyBirdCount = sleepingSessions.stream()
                .filter(SleepingSession::isNightSession)
                .map(SleepingSession::getNightType)
                .filter(nightType -> nightType == NightType.EARLY_BIRD)
                .count();

        NightType type;
        if (hummingBirdCount > Math.max(earlyBirdCount, nightOwlCount) || nightOwlCount == earlyBirdCount) {
            type = NightType.HUMMINGBIRD;
        } else if (nightOwlCount > earlyBirdCount) {
            type = NightType.NIGHT_OWL;
        } else {
            type = NightType.EARLY_BIRD;
        }

        final String details = "Тип снов";
        return new NightTypeResult(type, details);
    }
}
