package ru.job4j.waitnotify;

public class CountBarrier {
    private final int total;

    private int count = 1000;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public synchronized void count() {
        if (count-- > 0) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            notifyAll();
            count();
        } else {
            System.out.println("count");
        }
    }

    public void await() {
        while (count >= total) {
            System.out.println(String.format("%s___Await %c", Thread.currentThread().toString(), count));
        }
    }

    public static void main(String[] args) throws Exception {
        CountBarrier countBarrier1 = new CountBarrier(5);
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                countBarrier1.count();
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                countBarrier1.await();
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }
}
