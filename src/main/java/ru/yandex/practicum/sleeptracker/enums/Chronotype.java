package ru.yandex.practicum.sleeptracker.enums;

/**
 * Перечисление типов хронотип-ов.
 *
 * <p>Каждый тип сна имеет собственное название, которое отображается при вызове {@link #toString()}.</p>
 */
public enum Chronotype {
    /**
     * «Жаворонок» — если время засыпания было до 22:00, а время пробуждения до — 7:00.
     */
    ROOSTER("Жаворонок"),
    /**
     * «Сова» — если время засыпания было после 23:00, а время пробуждения — после 9:00.
     */
    OWL("Сова"),
    /**
     * Голубь – во всех остальных случаях.
     */
    PIGEON("Голубь");

    private final String displayName;

    Chronotype(String name) {
        this.displayName = name;
    }

    /**
     * Возвращает строковое представление типа хроноти-па.
     *
     * @return название, отображаемое пользователю (например «Сова»).
     */
    @Override
    public String toString() {
        return displayName;
    }
}

