package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.analytics.ChronotypeClassificationFunction;
import ru.yandex.practicum.sleeptracker.enums.Chronotype;
import ru.yandex.practicum.sleeptracker.enums.SleepQuality;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.yandex.practicum.sleeptracker.TestUtils.makeSession;

public class ChronotypeClassificationFunctionTest {

    @Test
    void owlType() {
        List<SleepingSession> sessions = List.of(
                makeSession("2025-10-01T23:30", "2025-10-02T09:30", SleepQuality.GOOD)
        );
        SleepAnalysisResult<Chronotype> result =
                new ChronotypeClassificationFunction().apply(sessions);

        assertEquals(Chronotype.OWL, result.getValue());
    }

    @Test
    void roosterType() {
        List<SleepingSession> sessions = List.of(
                makeSession("2025-10-01T21:00", "2025-10-02T06:30", SleepQuality.NORMAL)
        );
        SleepAnalysisResult<Chronotype> result =
                new ChronotypeClassificationFunction().apply(sessions);

        assertEquals(Chronotype.ROOSTER, result.getValue());
    }

    @Test
    void birdType() {
        List<SleepingSession> sessions = List.of(
                makeSession("2025-10-01T22:30", "2025-10-02T08:00", SleepQuality.BAD)
        );
        SleepAnalysisResult<Chronotype> result =
                new ChronotypeClassificationFunction().apply(sessions);

        assertEquals(Chronotype.PIGEON, result.getValue());
    }

    @Test
    void tieFallsBackToBird() {
        List<SleepingSession> sessions = List.of(
                makeSession("2025-10-01T23:30", "2025-10-02T09:30", SleepQuality.GOOD),
                makeSession("2025-10-03T21:00", "2025-10-04T06:30", SleepQuality.NORMAL)
        );
        SleepAnalysisResult<Chronotype> result =
                new ChronotypeClassificationFunction().apply(sessions);

        assertEquals(Chronotype.PIGEON, result.getValue());
    }

    @Test
    void daytimeSessionsIgnored() {
        List<SleepingSession> sessions = List.of(
                makeSession("2025-10-01T14:00", "2025-10-01T15:30", SleepQuality.GOOD)
        );
        SleepAnalysisResult<Chronotype> result =
                new ChronotypeClassificationFunction().apply(sessions);

        assertEquals(Chronotype.PIGEON, result.getValue());
    }
}
