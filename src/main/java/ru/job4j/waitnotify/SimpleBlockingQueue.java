package ru.job4j.waitnotify;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();
    private final int maxCount;

    public SimpleBlockingQueue(int maxCount) {
        this.maxCount = maxCount;
    }

    public void offer(T value) throws InterruptedException {
        synchronized (this) {
            while (queue.size() == maxCount) {
                this.wait();
            }
            queue.add(value);
            notifyAll();
        }
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int size() {
        return queue.size();
    }

    public T poll() throws InterruptedException {
        synchronized (this) {
            T result;
            while (queue.size() == 0) {
                wait();
            }
            result = queue.poll();
            notifyAll();
            return result;
        }
    }
}