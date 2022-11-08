package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public final class ParseFile {
    public static final int UNICODE_FILTER = 0x80;

    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public synchronized String getContent(Predicate<Character> filter) throws IOException {
        StringBuilder output = new StringBuilder();
        try (InputStream i = new FileInputStream(file)) {
            byte[] buffer = i.readAllBytes();
            for (byte b : buffer) {
                if (filter.test((char) b)) {
                    output.append(b);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return output.toString();
    }

    public synchronized String getAllContent() throws IOException {
        return getContent(i -> true);
    }

    public synchronized String getUnicodeContent() throws IOException {
        return getContent(i -> i < UNICODE_FILTER);
    }
}
