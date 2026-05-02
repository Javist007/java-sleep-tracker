package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.analytics.MaxDurationFunction;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class MaxDurationFunctionTest {

    @Test
    void emptyList() {
        List<SleepingSession> sessions = List.of();
        SleepAnalysisResult<Long> result = new MaxDurationFunction().apply(sessions);

        assertEquals(0L, result.getValue());
    }

    @Test
    void maxCalculation() {
        List<SleepingSession> sessions = TestUtils.sampleSessions();
        SleepAnalysisResult<Long> result = new MaxDurationFunction().apply(sessions);

        assertEquals(500L, result.getValue());
    }
}

