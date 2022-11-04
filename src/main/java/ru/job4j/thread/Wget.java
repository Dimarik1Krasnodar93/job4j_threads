package ru.job4j.thread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class Wget implements Runnable {
    private final String url;
    private final int speed;
    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        String file = url;
        try (BufferedInputStream in = new BufferedInputStream(new URL(file).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("pom_tmp.xml")) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            long start;
            long end;
            while (true) {
                start = System.currentTimeMillis();
                bytesRead = in.read(dataBuffer, 0, 1024);
                end = System.currentTimeMillis();
                if (bytesRead == -1) {
                    break;
                }
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                try {
                    if (end - start < speed) {
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String url = args[0];
        if (!validate(url)) {
            throw new IllegalArgumentException();
        }
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }
    private static boolean validate(String url) {
        try {
            new URL(url).toURI();
            return true;
        } catch (Exception exception) {
            System.out.println("Illegal arg exception");
            return false;
        }
    }
}