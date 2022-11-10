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
    private int maxCount;

    public SimpleBlockingQueue(int maxCount) {
        this.maxCount = maxCount;
    }

    public void offer(T value) throws InterruptedException {
        while (queue.size() == maxCount) {
            System.out.println("Offer wait");
            this.wait();
        }
        queue.add(value);
        System.out.println(String.format("Queue add value, %s", value.toString()));
        notifyAll();
    }

    public T poll() {
        T result;
        while (queue.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        result = queue.poll();
        System.out.println(String.format("Get poll, %s ", result.toString()));
        notifyAll();
        return result;
    }
}