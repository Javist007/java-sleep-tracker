package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.analytics.MinDurationFunction;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class MinDurationFunctionTest {

    @Test
    void emptyList() {
        List<SleepingSession> sessions = List.of();
        SleepAnalysisResult<Long> result =
                new MinDurationFunction().apply(sessions);

        assertEquals(0L, result.getValue());
    }

    @Test
    void minCalculation() {
        List<SleepingSession> sessions = TestUtils.sampleSessions();
        SleepAnalysisResult<Long> result =
                new MinDurationFunction().apply(sessions);

        assertEquals(45L, result.getValue());
    }
}

