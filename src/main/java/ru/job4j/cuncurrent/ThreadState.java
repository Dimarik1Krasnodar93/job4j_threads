package ru.job4j.cuncurrent;

public class ThreadState extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread());
    }
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> { }
        );
        Thread second = new Thread(
                () -> { }
        );
        first.setName("first");
        second.setName("second");
        System.out.println(first.getState());
        first.start();
        while (first.getState() != Thread.State.TERMINATED
                && second.getState() != State.TERMINATED) {
            System.out.println(first.getState());
        }
        System.out.println(first.getState());
        System.out.println("All threads has been terminated");
    }
}
