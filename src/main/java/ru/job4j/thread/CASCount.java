package ru.job4j.thread;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>(0);

    public void increment() {
        Integer temp;
        Integer tempNew;
        do {
            temp = count.get();
            tempNew = temp + 1;
        } while (!count.compareAndSet(temp, tempNew));
    }

    public int get() {
        return count.get();
    }
}
