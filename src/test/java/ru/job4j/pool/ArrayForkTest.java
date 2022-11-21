package ru.job4j.pool;

import org.junit.Before;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@Disabled
class ArrayForkTest {

    int[] array;
    private static final int SIZE_ELEMENTS = 10000;

    @Before
    void init() {
        array = new int[SIZE_ELEMENTS];
        Random random = new Random(ArrayFork.MAX_VALUE);
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt();
        }
    }

    @Test
    void findIndex() {
        init();
        ArrayFork arrayFork = new ArrayFork(array);
        int result = arrayFork.findIndex(array[123]);
        int expected = array[123];
        assertThat(result).isEqualTo(expected);
    }
}