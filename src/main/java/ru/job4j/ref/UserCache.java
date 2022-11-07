package ru.job4j.ref;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class UserCache {
    private final ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();
    private final AtomicInteger id = new AtomicInteger();

    public synchronized void add(User user) {
        users.put(id.incrementAndGet(), user);
    }

    public synchronized User findById(int id) {
        return users.get(id);
    }

    public synchronized List<User> findAll() {
        return users.entrySet().stream().map(i -> i.getValue()).collect(Collectors.toList());
    }
}
