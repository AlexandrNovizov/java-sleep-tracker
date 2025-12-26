package ru.yandex.practicum.sleeptracker;

public enum NightType {
    NIGHT_OWL,
    EARLY_BIRD,
    HUMMINGBIRD;

    @Override
    public String toString() {
        switch (this) {
            case NIGHT_OWL:
                return  "Сова";
            case EARLY_BIRD:
                return "Жаворонок";
            case HUMMINGBIRD:
                return "Голубь";
            case null, default:
                throw new IllegalStateException("Нет строки для " + this);
        }
    }
}
