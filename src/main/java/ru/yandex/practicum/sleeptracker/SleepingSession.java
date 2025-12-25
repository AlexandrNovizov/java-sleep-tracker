package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.exceptions.IllegalTimeRangeException;
import ru.yandex.practicum.sleeptracker.exceptions.InvalidStringFormatException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SleepingSession {

    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

    final LocalDateTime start;
    final LocalDateTime end;
    final SleepQuality quality;

    public SleepingSession(LocalDateTime start, LocalDateTime end, SleepQuality quality) {
        if (!start.isBefore(end)) {
            throw new IllegalTimeRangeException("Начало сессии должно быть до ее конца, start=" + start + " end=" + end);
        }
        this.start = start;
        this.end = end;
        this.quality = quality;
    }

    public static List<SleepingSession> readFile(String filepath) throws IOException {
        Path path = Path.of(filepath);
        if (!Files.exists(path)) {
            throw new FileNotFoundException("Файл лога по пути " + path + " не найден!");
        }

        try(Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
            return lines
                    .map(SleepingSession::fromString)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    public static SleepingSession fromString(String session) throws InvalidStringFormatException {
        String[] parts = session.split(";");
        if (parts.length != 3) {
            throw new InvalidStringFormatException("Строка " + session + " не соответствует формату");
        }

        try {
            LocalDateTime startTime = LocalDateTime.parse(parts[0], FORMATTER);
            LocalDateTime endTime = LocalDateTime.parse(parts[1], FORMATTER);
            SleepQuality quality = SleepQuality.valueOf(parts[2]);
            return new SleepingSession(startTime, endTime, quality);
        } catch (IllegalArgumentException | DateTimeParseException e) {
            throw new InvalidStringFormatException("Строка " + session + " не соответствует формату");
        }
    }

    public boolean isNightSession() {
        if (start.toLocalDate().equals(end.toLocalDate())) {
            return start.getHour() < 6;
        }

        return true;
    }

    public NightType nightType() {

        LocalDateTime boundsStart = LocalDateTime.of(start.toLocalDate(), LocalTime.of(23, 0));
        LocalDateTime boundsEnd = LocalDateTime.of(end.toLocalDate(), LocalTime.of(9, 0));

        if (start.toLocalDate().equals(end.toLocalDate())) {
            boundsStart = boundsStart.minusDays(1);
        }

        if (start.isAfter(boundsStart) && end.isAfter(boundsEnd)) {
            return NightType.NIGHT_OWL;
        }

        boundsStart = LocalDateTime.of(boundsStart.toLocalDate(), LocalTime.of(22, 0));
        boundsEnd = LocalDateTime.of(boundsEnd.toLocalDate(), LocalTime.of(7, 0));

        if (start.isBefore(boundsStart) && end.isBefore(boundsEnd)) {
            return NightType.EARLY_BIRD;
        }

        return NightType.HUMMINGBIRD;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public SleepQuality getQuality() {
        return quality;
    }
}
