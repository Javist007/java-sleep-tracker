package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.analytics.NoSleepNightCountFunction;
import ru.yandex.practicum.sleeptracker.enums.SleepQuality;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.yandex.practicum.sleeptracker.TestUtils.makeSession;

public class NoSleepNightCountFunctionTest {

    @Test
    void emptyList() {
        List<SleepingSession> sessions = List.of();
        SleepAnalysisResult<Long> result =
                new NoSleepNightCountFunction().apply(sessions);

        assertEquals(0L, result.getValue());
    }

    @Test
    void exampleLogFromTask() {
        List<SleepingSession> sessions = TestUtils.sampleSessions();
        SleepAnalysisResult<Long> result =
                new NoSleepNightCountFunction().apply(sessions);

        assertEquals(20L, result.getValue());
    }

    @Test
    void firstSessionAfterNoon() {
        List<SleepingSession> sessions = List.of(
                makeSession("2025-10-01T13:00", "2025-10-01T15:00", SleepQuality.NORMAL),
                makeSession("2025-10-02T23:30", "2025-10-03T06:20", SleepQuality.GOOD)
        );
        SleepAnalysisResult<Long> result =
                new NoSleepNightCountFunction().apply(sessions);

        assertEquals(1L, result.getValue());
    }

    @Test
    void monthBoundary() {
        List<SleepingSession> sessions = List.of(
                makeSession("2025-10-31T23:00", "2025-11-01T07:00", SleepQuality.GOOD),
                makeSession("2025-11-02T22:30", "2025-11-03T05:50", SleepQuality.NORMAL)
        );
        SleepAnalysisResult<Long> result =
                new NoSleepNightCountFunction().apply(sessions);

        assertEquals(1L, result.getValue());
    }
}