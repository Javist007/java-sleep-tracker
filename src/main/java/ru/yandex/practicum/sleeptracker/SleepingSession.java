package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.enums.SleepQuality;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Модель одной сессии сна.
 *
 * <p>Содержит время начала и окончания сна, а также оценку качества сна.</p>
 */
public class SleepingSession {

    private final LocalDateTime start;
    private final LocalDateTime end;
    private final SleepQuality quality;

    /**
     * Создаёт объект сессии сна.
     *
     * @param start   время начала сна. Не может быть {@code null}.
     * @param end     время окончания сна. Должно быть не раньше, чем {@code start}.
     * @param quality качество сна (GOOD, NORMAL, BAD). Не может быть {@code null}.
     * @throws IllegalArgumentException если {@code end} находится до {@code start}.
     */
    public SleepingSession(LocalDateTime start, LocalDateTime end, SleepQuality quality) {
        if (end.isBefore(start)) {
            throw new IllegalArgumentException("Время окончания до времени начала");
        }
        this.start = Objects.requireNonNull(start);
        this.end = Objects.requireNonNull(end);
        this.quality = Objects.requireNonNull(quality);
    }

    /**
     * Возвращает длительность сна в минутах.
     *
     * @return количество минут, проведённых во сне (разница между {@code end} и {@code start}).
     */
    public long durationMinutes() {
        return Duration.between(start, end).toMinutes();
    }

    /**
     * Возвращает время начала сессии сна.
     *
     * @return момент времени начала.
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * Возвращает время окончания сессии сна.
     *
     * @return момент времени окончания.
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * Получить качество сна для данной сессии.
     *
     * @return значение {@link SleepQuality}.
     */
    public SleepQuality getQuality() {
        return quality;
    }

    /**
     * Строковое представление объекта: поля в формате JSON‑подобного вида.
     *
     * @return строка вида «SleepingSession{start=..., end=..., quality=...}».
     */
    @Override
    public String toString() {
        return "SleepingSession{" +
                "start=" + start +
                ", end=" + end +
                ", quality=" + quality +
                '}';
    }
}

