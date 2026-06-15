package ru.yandex.practicum.sleeptracker.analytics;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;
import java.util.function.Function;

/**
 * Функция, рассчитывающая среднюю длительность всех сессий сна в минутах.
 *
 * <p>Метод {@link #apply(List)} возвращает объект {@link SleepAnalysisResult}
 * со строковым описанием «Средняя продолжительность (мин)» и округленным значением
 * среднего количества минут. Если список сессий пуст, результат будет {@code 0.0}.</p>
 */
public class AvgDurationFunction implements Function<List<SleepingSession>, SleepAnalysisResult<Double>> {

    /**
     * Рассчитывает среднее значение длительности сна по списку сессий.
     *
     * @param sessions список объектов {@link SleepingSession}. При пустом списке
     *                 возвращается {@code 0.0}.
     * @return результат анализа в виде {@link SleepAnalysisResult}
     * со строковым описанием и округленной средней длительностью (мин).
     */
    @Override
    public SleepAnalysisResult<Double> apply(List<SleepingSession> sessions) {
        double avg = sessions.stream()
                .mapToLong(SleepingSession::durationMinutes)
                .average()
                .orElse(0.0);

        double roundedAvg = Math.round(avg * 10) / 10.0;

        return new SleepAnalysisResult<>("Средняя продолжительность (мин)", roundedAvg);
    }
}
