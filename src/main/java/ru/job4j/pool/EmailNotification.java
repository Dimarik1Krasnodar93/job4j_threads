package ru.job4j.pool;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {

    private ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors());

    private Map<String, String> sendedMessages = new HashMap<>();

    public ExecutorService getPool() {
        return pool;
    }
    public Map<String, String> getSendedMessages() {
        return sendedMessages;
    }

    public void emailTo(User user) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                String subject = String.format("Notification %s to email %s.", user.username(), user.email());
                String body = String.format("Add a new event to %s", user.username());
                send(subject, body, user.email());
            }
        });
    }

    public void close() {
        pool.shutdown();
    }

    void send(String subject, String body, String email) {
        sendedMessages.putIfAbsent(subject, email);
    }
}
