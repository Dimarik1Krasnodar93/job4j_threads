package ru.job4j.pool;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ArrayFork<T> extends RecursiveTask<Integer> {

    private T[] array;
    public static final int MAX_VALUE = 1000;
    private int countThreads;
    private ForkJoinPool forkJoinPool;
    private static final int MIN_ELEMENTS_TO_PARALLEL = 10;
    private T elementSearch;
    private int indexFrom;
    private int indexTo;

    public ArrayFork(T[] array, T elementSearch) {
        this.array = array;
        this.elementSearch = elementSearch;
    }
    public ArrayFork(T[] array, T elementSearch, int indexFrom, int indexTo) {
        this.array = array;
        this.elementSearch = elementSearch;
        this.indexFrom = indexFrom;
        this.indexTo = indexTo;
        forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
    }

    public ArrayFork(T[] array) {
        forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        this.array = array;
    }


    public int findIndex(T elementSearch) {
        this.elementSearch = elementSearch;
        return 0;
    }

    int findLine(int from, int to) {
        for (int i = from; i <= to; i++) {
            if (elementSearch.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected Integer compute() {
        if (indexTo - indexFrom < 10) {
            return findLine(indexFrom, indexTo);
        }
        int middle = (indexFrom + indexTo) / 2;
        ArrayFork leftFork = new ArrayFork(array, elementSearch, indexFrom, middle);
        ArrayFork rightFork = new ArrayFork(array, elementSearch, middle + 1, indexTo);
        leftFork.fork();
        var resLeft = leftFork.join();
        Integer resRight = rightFork.compute();
        return Math.max((int) resLeft, (int) resRight);
    }
}
