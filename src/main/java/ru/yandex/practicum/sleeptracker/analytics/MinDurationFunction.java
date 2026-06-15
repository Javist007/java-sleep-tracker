package ru.yandex.practicum.sleeptracker.analytics;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;
import java.util.function.Function;

/**
 * Функция поиска минимальной длительности сна среди всех сессий.
 */
public class MinDurationFunction implements Function<List<SleepingSession>, SleepAnalysisResult<Long>> {

    /**
     * Находит и возвращает минимальное значение продолжительности (минут) среди переданных сессий.
     *
     * @param sessions список объектов {@link SleepingSession}. При пустом списке результат будет
     *                 {@code 0L}.
     * @return результат анализа в виде {@link SleepAnalysisResult}
     * со строковым описанием «Минимальная продолжительность (мин)» и найденным минимумом.
     */
    @Override
    public SleepAnalysisResult<Long> apply(List<SleepingSession> sessions) {
        long min = sessions.stream()
                .mapToLong(SleepingSession::durationMinutes)
                .min()
                .orElse(0L);
        return new SleepAnalysisResult<>("Минимальная продолжительность (мин)", min);
    }
}

