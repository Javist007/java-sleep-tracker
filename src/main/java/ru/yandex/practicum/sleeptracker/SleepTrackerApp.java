package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.analytics.*;
import ru.yandex.practicum.sleeptracker.parser.Parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Точка входа приложения для анализа логов сна.
 *
 * <p>Приложение читает файл сессий сна, применяет набор аналитических функций
 * и выводит результаты в консоль. Запуск осуществляется из командной строки:
 * {@code java SleepTrackerApp <путь к лог файлу>}.</p>
 */
public class SleepTrackerApp {

    private static final List<Function<List<SleepingSession>, ? extends SleepAnalysisResult<?>>> ANALYSIS_FUNCTIONS =
            List.of(
                    new CountSessionsFunction(),
                    new MinDurationFunction(),
                    new MaxDurationFunction(),
                    new AvgDurationFunction(),
                    new CountBadQualityFunction(),
                    new NoSleepNightCountFunction(),
                    new ChronotypeClassificationFunction()
            );

    /**
     * Запускает приложение, читает лог-файл и выводит результаты анализа.
     *
     * @param args аргументы командной строки; первый элемент – путь к файлу сессий сна.
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Использование: java SleepTrackerApp <путь к лог файлу>");
            System.exit(1);
        }

        Path logPath = Path.of(args[0]);

        List<SleepingSession> sessions;
        try (Stream<String> lines = Files.lines(logPath)) {
            sessions = lines
                    .filter(line -> !line.trim().isEmpty())
                    .map(Parser::parse)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("Не удалось прочитать файл: " + e.getMessage());
            return;
        }

        ANALYSIS_FUNCTIONS.forEach(func -> {
            SleepAnalysisResult<?> res = func.apply(sessions);
            System.out.println(res);
        });
    }
}