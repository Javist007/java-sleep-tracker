package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.analytics.CountBadQualityFunction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountBadQualityFunctionTest {

    @Test
    void emptyList() {
        List<SleepingSession> sessions = List.of();
        SleepAnalysisResult<Long> result =
                new CountBadQualityFunction().apply(sessions);

        assertEquals(0L, result.getValue());
    }

    @Test
    void badSessionsCount() {
        List<SleepingSession> sessions = TestUtils.sampleSessions();
        SleepAnalysisResult<Long> result =
                new CountBadQualityFunction().apply(sessions);

        assertEquals(2L, result.getValue());
    }
}
