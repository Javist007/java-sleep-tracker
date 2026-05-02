package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.enums.SleepQuality;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Вспомогательные методы для тестов.
 */
public final class TestUtils {
    private TestUtils() {
    }

    public static SleepingSession session(String start,
                                          String end,
                                          SleepQuality quality) {
        return new SleepingSession(
                LocalDateTime.parse(start),
                LocalDateTime.parse(end),
                quality
        );
    }

    /**
     * Записей - 13
     * Сумма всех сессий = 4490 минут
     * Средняя длительность 345.4
     * Минимальная 45
     * Максимальная 500
     * Плохие (BAD) 2
     * <Временем логирования считаем интервал от начала первой сессии сна в файле до окончания последней.
     * При этом считаем, что пользователь носит часы не снимая — то есть не было сессий сна, которые не попали бы в файл.>
     * - ПО ТЗ!
     * - Исходя из первой и последней сессии (2025-10-01 - 2025-10-30) всего ночей 30
     * из них:
     * Бессонные (2025-10-06, 2025-10-10) - 2
     * Отсутствующие 18
     * Итого бессонные (ожидание) - 20
     */
    public static List<SleepingSession> sampleSessions() {
        return List.of(
                session("2025-10-01T23:15", "2025-10-02T07:30", SleepQuality.GOOD), // 495
                session("2025-10-02T23:50", "2025-10-03T06:40", SleepQuality.NORMAL), // 410
                session("2025-10-03T14:10", "2025-10-03T15:00", SleepQuality.NORMAL), // 50
                session("2025-10-03T23:40", "2025-10-04T08:00", SleepQuality.BAD), // 500
                session("2025-10-05T00:10", "2025-10-05T06:20", SleepQuality.GOOD), // 370
                session("2025-10-05T13:30", "2025-10-05T14:15", SleepQuality.NORMAL), // 45
                session("2025-10-06T22:30", "2025-10-07T05:50", SleepQuality.GOOD), // 440
                session("2025-10-07T23:45", "2025-10-08T06:30", SleepQuality.GOOD), // 405
                session("2025-10-08T23:50", "2025-10-09T07:10", SleepQuality.GOOD), // 440
                session("2025-10-10T13:00", "2025-10-10T14:30", SleepQuality.NORMAL), // 90
                session("2025-10-10T23:55", "2025-10-11T06:10", SleepQuality.GOOD), // 375
                session("2025-10-11T23:10", "2025-10-12T07:00", SleepQuality.BAD), // 470
                session("2025-10-30T23:50", "2025-10-31T06:30", SleepQuality.GOOD) // 400
        );
    }

    public static SleepingSession makeSession(String start,
                                              String end,
                                              SleepQuality quality) {
        return new SleepingSession(
                LocalDateTime.parse(start),
                LocalDateTime.parse(end),
                quality
        );
    }
}

