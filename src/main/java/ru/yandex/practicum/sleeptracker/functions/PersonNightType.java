package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.NightType;
import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.results.NightTypeResult;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PersonNightType extends SleepAnalysisFunction<NightType> {
    @Override
    public NightTypeResult apply(List<? extends SleepingSession> sleepingSessions) {
        super.apply(sleepingSessions);

        Map<NightType, Long> nightTypeCount = sleepingSessions.stream()
                .filter(SleepingSession::isNightSession)
                .collect(Collectors.groupingBy(
                        SleepingSession::getNightType,
                        Collectors.counting()
                ));

        long hummingBirdCount = nightTypeCount.getOrDefault(NightType.HUMMINGBIRD, 0L);
        long nightOwlCount = nightTypeCount.getOrDefault(NightType.NIGHT_OWL, 0L);
        long earlyBirdCount = nightTypeCount.getOrDefault(NightType.EARLY_BIRD, 0L);

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
