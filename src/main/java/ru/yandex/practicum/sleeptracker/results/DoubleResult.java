package ru.yandex.practicum.sleeptracker.results;

public class DoubleResult extends SleepAnalysisResult<Double> {
    public DoubleResult(Double result, String details) {
        super(result, details);
    }

    @Override
    public String toString() {
        return String.format("%s: %.1f", details, result);
    }
}
