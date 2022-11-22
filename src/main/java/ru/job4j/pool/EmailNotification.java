package ru.job4j.pool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {

    private ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors());

    private final List<MessageInfo> sendedMessages = new ArrayList<>();

    public List<MessageInfo> getSendedMessages() {
        return new ArrayList<>(sendedMessages);
    }

    public void emailTo(User user) {
        pool.submit(() -> {
            String subject = String.format("Notification %s to email %s.", user.username(), user.email());
            String body = String.format("Add a new event to %s", user.username());
            send(subject, body, user.email());
        });
    }

    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void send(String subject, String body, String email) {
        sendedMessages.add(new MessageInfo(subject, body, email));
    }

    public class MessageInfo {
        private String subject;
        private String body;
        private String email;

        public MessageInfo(String subject, String body, String email) {
            this.subject = subject;
            this.body = body;
            this.email = email;
        }
    }
}
