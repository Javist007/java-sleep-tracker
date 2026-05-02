package ru.yandex.practicum.sleeptracker;

/**
 * Обёртка над результатом анализа сна.
 */
public final class SleepAnalysisResult<T> {

    private final String description;
    private final T value;

    /**
     * Создаёт новый результат анализа.
     *
     * @param description строковое описание результата (например «Количество сессий»).
     * @param value       реальное значение, содержащееся в результате.
     */
    public SleepAnalysisResult(String description, T value) {
        this.description = description;
        this.value = value;
    }

    /**
     * Возвращает строку‑описание результата анализа.
     *
     * @return описание результата.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Получает значение, содержащееся в результате.
     *
     * @return реальное значение результата (может быть {@code null}).
     */
    public T getValue() {
        return value;
    }

    /**
     * Строковое представление объекта: «описание: значение».
     *
     * @return строка вида «description: value».
     */
    @Override
    public String toString() {
        return description + ": " + value;
    }
}

