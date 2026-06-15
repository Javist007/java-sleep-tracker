package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.analytics.CountSessionsFunction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CountSessionsFunctionTest {

    @Test
    void emptyList() {
        List<SleepingSession> sessions = List.of();
        SleepAnalysisResult<Long> result = new CountSessionsFunction().apply(sessions);

        assertEquals(0L, result.getValue());
        assertEquals("Количество сессий сна", result.getDescription());
    }

    @Test
    void multipleSessions() {
        List<SleepingSession> sessions = TestUtils.sampleSessions();
        SleepAnalysisResult<Long> result = new CountSessionsFunction().apply(sessions);

        assertEquals(13L, result.getValue());
    }
}

