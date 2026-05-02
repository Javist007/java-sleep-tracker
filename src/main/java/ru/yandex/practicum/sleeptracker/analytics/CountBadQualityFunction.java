package ru.yandex.practicum.sleeptracker.analytics;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.enums.SleepQuality;

import java.util.List;
import java.util.function.Function;

/**
 * Функция подсчёта количества сессий сна со «плохим» качеством.
 */
public class CountBadQualityFunction implements Function<List<SleepingSession>, SleepAnalysisResult<Long>> {

    /**
     * Подсчитывает, сколько из переданных сессий сна имеют качество {@link SleepQuality#BAD}.
     *
     * @param sessions список объектов {@link SleepingSession}. При пустом списке
     *                 возвращается {@code 0L}.
     * @return результат анализа в виде {@link SleepAnalysisResult}
     * со строковым описанием «Количество плохих сессий» и количеством таких сессий.
     */
    @Override
    public SleepAnalysisResult<Long> apply(List<SleepingSession> sessions) {
        long bad = sessions.stream()
                .filter(s -> s.getQuality() == SleepQuality.BAD)
                .count();
        return new SleepAnalysisResult<>("Количество плохих сессий", bad);
    }
}
