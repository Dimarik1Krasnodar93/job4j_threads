package ru.job4j.pool;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@Disabled
class ThreadPoolTest {
    @Test
    void testPool() {
        ThreadPool threadPool = new ThreadPool();
        threadPool.workAllThreads();
        int expected = Runtime.getRuntime().availableProcessors();
        threadPool.shutdown();
        assertThat(threadPool.tasks().size()).isEqualTo(expected);
    }
}