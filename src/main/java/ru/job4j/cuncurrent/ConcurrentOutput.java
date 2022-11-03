package ru.job4j.cuncurrent;

public class ConcurrentOutput {
    public static void main(String[] args) {
        Thread another = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        Thread second = new Thread(
                () -> System.out.println(Thread.currentThread().getName()));
        second.setName("Second");
        another.run();
        second.start();
        System.out.println(Thread.currentThread().getName() + "_test");
    }
}
