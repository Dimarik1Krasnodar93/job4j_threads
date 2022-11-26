package ru.job4j.pool;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class ArrayForkTest {

    Integer[] array;
    private static final int SIZE_ELEMENTS = 10000;
    private static final int MAX_VALUE = 100;

    @BeforeEach
    public void init() {
        array = new Integer[SIZE_ELEMENTS];
        Random random = new Random(MAX_VALUE);
        for (int i = 0; i < SIZE_ELEMENTS; i++) {
            array[i] = random.nextInt();
        }
    }

    @Test
    public void testValue200() {
        int searchValue = 200;
        int expected = 5444;
        array[5] = searchValue;
        array[expected] = searchValue;
        ArrayFork arrayFork = new ArrayFork(array, searchValue, 0, array.length - 1);
        int result = arrayFork.compute();
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void testValueNotFound() {
        int searchValue = -1;
        int expected = -1;
        ArrayFork arrayFork = new ArrayFork(array, searchValue, 0, array.length - 1);
        int result = arrayFork.compute();
        assertThat(result).isEqualTo(expected);
    }
}