package ru.job4j.pool;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ArrayFork<T> extends RecursiveTask<Integer> {

    private T[] array;
    private T elementSearch;
    private int indexFrom;
    private int indexTo;

    public ArrayFork(T[] array, T elementSearch, int indexFrom, int indexTo) {
        this.array = array;
        this.elementSearch = elementSearch;
        this.indexFrom = indexFrom;
        this.indexTo = indexTo;
    }

    public static <T> int findIndex(T elementSearch, T[] array) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ArrayFork<>(array, elementSearch, 0, array.length - 1));
    }

    private int findLine() {
        for (int i = indexFrom; i <= indexTo; i++) {
            if (elementSearch.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected Integer compute() {
        if (indexTo - indexFrom < 10) {
            return findLine();
        }
        int middle = (indexFrom + indexTo) / 2;
        ArrayFork<T> leftFork = new ArrayFork<>(array, elementSearch, indexFrom, middle);
        ArrayFork<T> rightFork = new ArrayFork<>(array, elementSearch, middle + 1, indexTo);
        leftFork.fork();
        rightFork.fork();
        var resLeft = leftFork.join();
        var resRight = rightFork.join();
        return Math.max(resLeft, resRight);
    }
}

