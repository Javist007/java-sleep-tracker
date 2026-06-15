package ru.yandex.practicum.sleeptracker.analytics;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;
import java.util.function.Function;

/**
 * Функция поиска максимальной длительности сна среди всех сессий.
 */
public class MaxDurationFunction implements Function<List<SleepingSession>, SleepAnalysisResult<Long>> {

    /**
     * Находит и возвращает максимальное значение продолжительности (минут) среди переданных сессий.
     *
     * @param sessions список объектов {@link SleepingSession}. При пустом списке результат будет
     *                 {@code 0L}.
     * @return результат анализа в виде {@link SleepAnalysisResult}
     * со строковым описанием «Максимальная продолжительность (мин)» и найденным максимумом.
     */
    @Override
    public SleepAnalysisResult<Long> apply(List<SleepingSession> sessions) {
        long max = sessions.stream()
                .mapToLong(SleepingSession::durationMinutes)
                .max()
                .orElse(0L);
        return new SleepAnalysisResult<>("Максимальная продолжительность (мин)", max);
    }
}
