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
        String fileName = String.format("%s, %s", "_temp_", file.substring(file.lastIndexOf('/') + 1));
        try (BufferedInputStream in = new BufferedInputStream(new URL(file).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            long start;
            long end;
            start = System.currentTimeMillis();
            bytesRead = in.read(dataBuffer, 0, 1024);
            end = System.currentTimeMillis();
            int downloadData = 0;
            while (bytesRead != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                downloadData += bytesRead;
                try {
                    if (downloadData >= speed && end - start < 1000) {
                        downloadData = 0;
                        Thread.sleep(1000 - (end - start));
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                bytesRead = in.read(dataBuffer, 0, 1024);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        validate(args);
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }
    private static void validate(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Illegal count of arguments");
        } else {
            try {
                new URL(args[0]).toURI();
            } catch (Exception exception) {
                throw new IllegalArgumentException("Illegal arg exception");
            }
        }
    }
}