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
            long start = System.currentTimeMillis();
            long end;
            int downloadData = 0;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {

                fileOutputStream.write(dataBuffer, 0, bytesRead);
                if (downloadData >= speed) {
                    downloadData += bytesRead;
                    end = System.currentTimeMillis();
                    start = System.currentTimeMillis();
                    try {
                        if (end - start < 1000) {
                            Thread.sleep(1000 - (end - start));
                        }
                        downloadData = 0;

                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                bytesRead = in.read(dataBuffer, 0, 1024);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
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