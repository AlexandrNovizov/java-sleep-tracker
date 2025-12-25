package ru.yandex.practicum.sleeptracker.results;

public class SleepAnalysisResult<T> {

    T result;
    String details;

    public SleepAnalysisResult(T result, String details) {
        this.result = result;
        this.details = details;
    }

    public T getResult() {
        return result;
    }

    public String getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return details + ": " + result;
    }
}
