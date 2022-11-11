package ru.job4j.buffer;

import ru.job4j.waitnotify.SimpleBlockingQueue;

public class ParallelSearch {

    public static void main(String[] args) {
        System.out.println("Commit for task git: 3.1. Добавить изменения в последний коммит. [#504829]");
        System.out.println("1Commit for task git: 3.1. Добавить изменения в последний коммит. [#504830]");
        System.out.println("2Commit for task git: 3.1. Добавить изменения в последний коммит. [#504830]");
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(10);
        final Thread consumer = new Thread(
                () -> {
                    synchronized (queue) {
                        while (true) {
                            try {
                                System.out.println(queue.poll());
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                                Thread.currentThread().interrupt();
                                break;
                            }
                        }
                    }
                }
        );
        consumer.start();
        new Thread(
                () -> {
                    synchronized (queue) {
                        for (int index = 0; index != 3; index++) {
                            try {
                                queue.offer(index);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        queue.notifyAll();
                        consumer.interrupt();
                    }
                }

        ).start();
    }
}
