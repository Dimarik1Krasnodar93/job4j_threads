package ru.job4j.concurrent;

public class ThreadStop {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(
                () -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        try {
                            System.out.println("start...");
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            System.out.println(Thread.currentThread().isInterrupted());
                            System.out.println(Thread.currentThread().getState());
                            System.out.println("end ...");
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
        thread.join();
    }
}
