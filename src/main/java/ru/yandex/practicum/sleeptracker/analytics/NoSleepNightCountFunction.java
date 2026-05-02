package ru.yandex.practicum.sleeptracker.analytics;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.LongStream;

/**
 * Функция подсчёта количества ночей без сна.
 *
 * <p>Подсчитывает, сколько дней (ночей) в диапазоне дат всех сессий
 * не содержат ни одной сессии, пересекающей окно 00‑06. Если список пуст,
 * результатом будет {@code 0L}.</p>
 */
public class NoSleepNightCountFunction implements Function<List<SleepingSession>, SleepAnalysisResult<Long>> {

    /**
     * Выполняет подсчёт бессонных ночей по списку сессий.
     *
     * @param sessions список объектов {@link SleepingSession}. При пустом списке
     *                 возвращается результат «Бессонные ночи» со значением {@code 0L}.
     * @return результат анализа в виде {@link SleepAnalysisResult}
     * со строковым описанием «Бессонные ночи» и количеством таких дней.
     */
    @Override
    public SleepAnalysisResult<Long> apply(List<SleepingSession> sessions) {
        if (sessions.isEmpty()) return new SleepAnalysisResult<>("Бессонные ночи", 0L);

        List<SleepingSession> sorted = sessions.stream()
                .sorted(Comparator.comparing(SleepingSession::getStart))
                .toList();

        LocalDateTime overallStart = sorted.getFirst().getStart();
        LocalDateTime overallEnd = sorted.getLast().getEnd();

        LocalDate firstNightDate = overallStart.toLocalTime().isAfter(LocalTime.NOON)
                ? overallStart.toLocalDate().plusDays(1)
                : overallStart.toLocalDate();

        LocalDate lastNightDate = overallEnd.toLocalDate();

        long daysBetween = ChronoUnit.DAYS.between(firstNightDate, lastNightDate);
        if (daysBetween < 0) return new SleepAnalysisResult<>("Бессонные ночи", 0L);

        long noSleepCount = LongStream.rangeClosed(0, daysBetween)
                .mapToObj(firstNightDate::plusDays)
                .filter(night -> {
                    LocalDateTime nightStart = night.atStartOfDay();
                    LocalDateTime nightEnd = nightStart.plusHours(6);

                    return sorted.stream()
                            .noneMatch(s ->
                                    s.getStart().isBefore(nightEnd) &&
                                            s.getEnd().isAfter(nightStart));
                })
                .count();

        return new SleepAnalysisResult<>("Бессонные ночи", noSleepCount);
    }
}
