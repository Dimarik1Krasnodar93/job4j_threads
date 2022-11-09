package ru.job4j.waitnotify;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();
    private int maxCount = 10;

    private final Object monitor = this;

    public SimpleBlockingQueue(int maxCount) {
        this.maxCount = maxCount;
    }

    public void runqueue(Iterator<T> iterator) {
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            if (random.nextInt() % 2 == 0) {
                while (iterator.hasNext()) {
                    try {
                        offer(iterator.next());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                poll();
            }
        }
    }

    public void offer(T value) throws InterruptedException {
        if (queue.size() == maxCount) {
            System.out.println("Offer wait");
            this.wait();
        } else {
            queue.add(value);
            System.out.println(String.format("Queue add value, %s", value.toString()));
        }
    }

    public T poll() {
        T result = null;
        if (queue.size() != 0) {
            result = queue.poll();
            System.out.println(String.format("Get poll, %s ", result.toString()));
            this.notifyAll();
        } else {
            try {
                System.out.println("Get poll wait");
                monitor.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}