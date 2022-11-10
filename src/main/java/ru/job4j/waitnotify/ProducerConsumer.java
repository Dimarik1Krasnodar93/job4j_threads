package ru.job4j.waitnotify;

import java.util.Iterator;
import java.util.List;

public class ProducerConsumer {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> values = List.of(1, 2, 3, 5, 6, 7, 1, 3, 44, 33, 22, 55, 6663);
        Iterator iterator = values.iterator();
        SimpleBlockingQueue<Integer> simpleBlockingQueue = new SimpleBlockingQueue(10);
        Thread queue = new Thread(
                () -> simpleBlockingQueue.poll());
        Thread costumers = new Thread(
                () -> {
                    if (iterator.hasNext()) {
                        try {
                            simpleBlockingQueue.offer(values.iterator().next());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                );
        queue.start();
        costumers.start();
        queue.join();
        costumers.join();
    }
}
