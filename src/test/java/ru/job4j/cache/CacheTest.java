package ru.job4j.cache;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CacheTest {

    @Test
    void add() {
        Cache cache = new Cache();
        assertThat(cache.add(new Base(1, 0))).isEqualTo(true);
    }

    @Test
    void updateTrue() {
        Cache cache = new Cache();
        cache.add(new Base(1, 0));
        assertThat(cache.update(new Base(1, 0))).isEqualTo(true);
    }

    @Test
    void updateFalse() {
        Cache cache = new Cache();
        cache.add(new Base(1, 0));
        assertThat(cache.update(new Base(2, 0))).isEqualTo(false);
    }

    @Test
    void delete() {
        Cache cache = new Cache();
        cache.add(new Base(1, 0));
        cache.delete(new Base(1, 0));
        assertThat(cache.getBase(1)).isNull();
    }
}