package ru.job4j.cuncurrent;

public class Wget {
    public static void main(String[] args) {
        Thread thread1 = new Thread(
                () -> {
                    try {
                        char ch;
                        for (int i = 0; i <= 100; i++) {
                            Thread.sleep(1000);
                            switch (i % 3) {
                                case 1: ch = '/'; break;
                                case 2: ch = '\\'; break;
                                default: ch = '|'; break;
                            }
                            System.out.print("\rLoading : " + ch);
                        }
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                });
        thread1.start();
    }
}
