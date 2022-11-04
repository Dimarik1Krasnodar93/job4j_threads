package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {
    @Override
    public void run() {
        int i = 0;
        char [] charArray = {'/', '\\', '|'};
        while (!Thread.currentThread().isInterrupted()) {
            System.out.print("\rLoading..." + charArray[i++ % charArray.length]);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print("\r load: " + Thread.currentThread().getName());

    }
    public static void main(String[] args) {
        Thread progress = new Thread(new ConsoleProgress());
        progress.setName("thread_process");
        progress.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        progress.interrupt();
    }
}
