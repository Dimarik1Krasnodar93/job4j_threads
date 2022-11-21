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
        for (Thread thread : threadPool.threads()) {
            try {
                threadPool.work(thread);
            } catch (InterruptedException e) {
                thread.interrupt();
            }
        }
        int expected = 1;
        threadPool.shutdown();
        assertThat(threadPool.tasks().size()).isEqualTo(expected);
    }
}