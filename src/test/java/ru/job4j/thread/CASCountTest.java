package ru.job4j.thread;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class CASCountTest {
    @Test
    void whenIncrement1000() throws InterruptedException {
        CASCount cascount = new CASCount();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                cascount.increment();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                cascount.increment();
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        int expected = 1000;
        assertThat(cascount.get()).isEqualTo(expected);
    }

    @Test
    void whenIncrement800() throws InterruptedException {
        CASCount cascount = new CASCount();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 200; i++) {
                cascount.increment();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 200; i++) {
                cascount.increment();
            }
        });
        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 200; i++) {
                cascount.increment();
            }
        });
        Thread thread4 = new Thread(() -> {
            for (int i = 0; i < 200; i++) {
                cascount.increment();
            }
        });
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
        int expected = 800;
        assertThat(cascount.get()).isEqualTo(expected);
    }
}