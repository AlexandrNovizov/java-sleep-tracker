package ru.yandex.practicum.sleeptracker.results;

public abstract class SleepAnalysisResult<T> {

    protected T result;
    protected String details;

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
