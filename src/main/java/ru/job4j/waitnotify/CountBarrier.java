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
        int maxCount = 1005;
        CountBarrier countBarrier1 = new CountBarrier(maxCount);
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < maxCount; i++) {
                countBarrier1.count();
            }
        });
        Thread thread2 = new Thread(() -> countBarrier1.await());
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }
}
