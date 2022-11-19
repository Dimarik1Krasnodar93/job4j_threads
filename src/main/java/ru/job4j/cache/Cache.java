package ru.job4j.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.put(model.getId(), model) == null;
    }

    public boolean update(Base model) {
       return memory.computeIfPresent(model.getId(), (key, value) -> {
            Base stored = memory.get(model.getId());
            if (stored == null) {
                return null;
            }
            if (stored.getVersion() != model.getVersion()) {
                throw new IllegalStateException("Versions are not equal");

            }
            return new Base(key, value.getVersion() + 1);
        }) != null;
    }

    public void delete(Base model) {
        memory.remove(model.getId());
    }

    public Base getBase(int id) {
        return memory.get(id);
    }
}
