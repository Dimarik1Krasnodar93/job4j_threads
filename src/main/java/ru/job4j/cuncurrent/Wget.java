package ru.job4j.cuncurrent;

public class Wget {
    public static void main(String[] args) {
        Thread thread1 = new Thread(
                () -> {
                    try {
                        for (int i = 0; i < 100; i++) {
                            Thread.sleep(1);
                            System.out.print("\rLoading : " + i  + "%");
                        }
                    } catch(InterruptedException ex) {
                        ex.printStackTrace();
                    }
                });
        thread1.start();
    }
}
