package ru.job4j.waitnotify;

public class CountBarrier {
    private final int total;

    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public void count() {
        synchronized (this) {
            count++;
            notifyAll();
        }
    }

    public synchronized void await() {
        synchronized (this) {
            while (count < total) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        CountBarrier countBarrier1 = new CountBarrier(5);
        Thread thread1 = new Thread(() -> countBarrier1.count());
        Thread thread2 = new Thread(() -> countBarrier1.await());
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }
}
