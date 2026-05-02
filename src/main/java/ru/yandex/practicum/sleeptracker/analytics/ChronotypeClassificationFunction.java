package ru.yandex.practicum.sleeptracker.analytics;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.enums.Chronotype;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Функция классификации хронотип-ов по данным о времени засыпания и пробуждения.
 *
 * <p>Алгоритм работает следующим образом:</p>
 * <ul>
 *   <li>Фильтруются только те сессии, которые пересекают хотя бы одно окно 00‑06.</li>
 *   <li>Для каждой такой сессии определяется хронотип по времени засыпания и пробуждения.</li>
 *   <li>Подсчитывается количество встречающихся хронотип-ов.</li>
 *   <li>Хронотип, имеющий наибольшее число совпадений, считается типом пользователя.
 *       При равенстве нескольких типов (или отсутствии подходящих сессий) результат
 *       фиксируется как {@link Chronotype#PIGEON} («Голубь»).</li>
 * </ul>
 */
public class ChronotypeClassificationFunction implements Function<List<SleepingSession>, SleepAnalysisResult<Chronotype>> {

    /**
     * Выполняет классификацию хронотип-ов на основе списка сессий сна.
     *
     * @param sessions список объектов {@link SleepingSession}. При пустом списке
     *                 возвращается строка «Тип хронотип-а» и значение {@code PIGEON}.
     * @return результат анализа в виде {@link SleepAnalysisResult}
     * со строковым описанием и определённым типом хронотип-а.
     */
    @Override
    public SleepAnalysisResult<Chronotype> apply(List<SleepingSession> sessions) {

        Map<Chronotype, Long> counts = sessions.stream()
                .filter(this::overlapsNightWindow)
                .map(this::determineChronotype)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        long maxCount = counts.values().stream()
                .max(Long::compareTo)
                .orElse(0L);

        List<Chronotype> topTypes = counts.entrySet().stream()
                .filter(e -> e.getValue() == maxCount)
                .map(Map.Entry::getKey)
                .toList();

        Chronotype result;
        if (topTypes.size() != 1) {
            result = Chronotype.PIGEON;
        } else {
            result = topTypes.getFirst();
        }

        return new SleepAnalysisResult<>("Тип хронотип-а", result);
    }

    /**
     * Проверяем, пересекает ли сессия хотя бы одно окно 00‑06
     */
    private boolean overlapsNightWindow(SleepingSession s) {
        LocalDateTime start = s.getStart();
        LocalDateTime end = s.getEnd();

        LocalDateTime nightStart1 = start.toLocalDate().atStartOfDay();
        LocalDateTime nightEnd1 = nightStart1.plusHours(6);
        if (start.isBefore(nightEnd1) && end.isAfter(nightStart1)) {
            return true;
        }

        LocalDateTime nightStart2 = nightStart1.plusDays(1);
        LocalDateTime nightEnd2 = nightStart2.plusHours(6);

        return start.isBefore(nightEnd2) && end.isAfter(nightStart2);
    }

    /**
     * Определяем хронотип по времени засыпания и пробуждения
     */
    private Chronotype determineChronotype(SleepingSession s) {
        LocalTime start = s.getStart().toLocalTime();
        LocalTime end = s.getEnd().toLocalTime();

        if (start.isAfter(LocalTime.of(23, 0)) && end.isAfter(LocalTime.of(9, 0))) {
            return Chronotype.OWL;
        } else if (start.isBefore(LocalTime.of(22, 0)) && end.isBefore(LocalTime.of(7, 0))) {
            return Chronotype.ROOSTER;
        } else {
            return Chronotype.PIGEON;
        }
    }
}
