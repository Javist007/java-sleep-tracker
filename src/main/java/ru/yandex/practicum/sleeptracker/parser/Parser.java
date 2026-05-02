package ru.yandex.practicum.sleeptracker.parser;

import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.enums.SleepQuality;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Вспомогательный класс для парсинга строк лога сна в объекты {@link SleepingSession}.
 *
 * <p>Строка должна иметь формат: «dd.MM.yy HH:mm;dd.MM.yy HH:mm;QUALITY»,
 * где QUALITY – одно из значений перечисления {@link SleepQuality}.</p>
 */
public class Parser {

    private Parser() {
    }

    /**
     * Разделитель параметров в строке.
     */
    private static final int INPUT_PARAMETER_DIVIDER = 3;
    /**
     * Индекс начала сна.
     */
    private static final int START_SLEEP = 0;
    /**
     * Индекс конца сна.
     */
    private static final int END_SLEEP = 1;
    /**
     * Индекс качества сна.
     */
    private static final int QUALITY = 2;
    /**
     * Формат даты и времени, используемый в логах.
     */
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yy[ yy] HH:mm");

    /**
     * Парсит строку лога в объект {@link SleepingSession}.
     *
     * @param line строка вида «dd.MM.yy HH:mm;dd.MM.yy HH:mm;QUALITY».
     * @return новый объект {@code SleepingSession} с разложенными полями.
     * @throws IllegalArgumentException если формат строки неверный
     *                                  (не ровно три части, неверное время или некорректное значение качества).
     */
    public static SleepingSession parse(String line) {
        String[] parts = line.split(";");
        if (parts.length != INPUT_PARAMETER_DIVIDER) {
            throw new IllegalArgumentException("Неверный формат записи: " + line);
        }
        LocalDateTime start = LocalDateTime.parse(parts[START_SLEEP].trim(), FORMATTER);
        LocalDateTime end = LocalDateTime.parse(parts[END_SLEEP].trim(), FORMATTER);
        SleepQuality quality = SleepQuality.valueOf(parts[QUALITY].trim());
        return new SleepingSession(start, end, quality);
    }
}
