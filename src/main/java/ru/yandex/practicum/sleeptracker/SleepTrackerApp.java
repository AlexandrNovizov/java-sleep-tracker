package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.exceptions.InvalidStringFormatException;
import ru.yandex.practicum.sleeptracker.functions.*;

import java.io.*;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.List;

public class SleepTrackerApp {

    static List<SleepingSession> sleepingSessions = new ArrayList<>();
    static List<SleepAnalysisFunction<?>> functions = new ArrayList<>();

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Программа принимает 1 аргумент - путь до файла!");
            return;
        }

        initFunctions();

        try {
            sleepingSessions = SleepingSession.readFile(args[0]);
        } catch (InvalidPathException e) {
            System.out.println("Недопустимый путь: " + args[0]);
            System.exit(-1);
        } catch (FileNotFoundException | InvalidStringFormatException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
            System.exit(-1);
        }

        try {
            applyFunctions();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static void applyFunctions() {
        functions.stream()
                .map(function -> function.apply(sleepingSessions))
                .peek(System.out::println)
                .allMatch(x -> true);
    }

    static void initFunctions() {
        functions.add(new MinSessionDuration());
        functions.add(new MaxSessionDuration());
        functions.add(new AverageSessionDuration());
        functions.add(new BadSessionsCount());
        functions.add(new SleeplessNightsCount());
        functions.add(new PersonNightType());
    }
}