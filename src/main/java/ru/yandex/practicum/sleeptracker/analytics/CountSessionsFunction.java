package ru.yandex.practicum.sleeptracker.analytics;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;
import java.util.function.Function;

/**
 * Функция подсчёта количества сессий сна.
 */
public class CountSessionsFunction implements Function<List<SleepingSession>, SleepAnalysisResult<Long>> {

    /**
     * Возвращает количество переданных сессий. Если список пуст, описание «Сессии отсутствуют» и
     * значение {@code 0L} выводятся в результате анализа.
     *
     * @param sessions список объектов {@link SleepingSession}. При пустом списке возвращается
     *                 {@link SleepAnalysisResult} со строковым описанием «Сессии отсутствуют».
     * @return результат анализа с количеством сессий сна (или 0 при отсутствии данных).
     */
    @Override
    public SleepAnalysisResult<Long> apply(List<SleepingSession> sessions) {
        if (sessions.isEmpty()) {
            return new SleepAnalysisResult<>("Количество сессий сна", 0L);
        }
        return new SleepAnalysisResult<>("Количество сессий сна", (long) sessions.size());
    }
}

