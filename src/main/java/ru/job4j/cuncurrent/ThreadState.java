package ru.job4j.cuncurrent;

public class ThreadState extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread());
    }
    public static void main(String[] args) {
        Thread first = new ThreadState();
        Thread second = new ThreadState();
        first.setName("first");
        second.setName("second");
        threadStartInfo(first);
        threadStartInfo(second);
        System.out.println("All threads has been terminated");
    }

    private static void threadStartInfo(Thread thread) {
        System.out.println(thread.getState());
        thread.start();
        while (thread.getState() != Thread.State.TERMINATED) {
            System.out.println(thread.getState());
        }
        System.out.println(thread.getState());
    }
}
