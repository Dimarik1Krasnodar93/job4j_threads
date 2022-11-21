package ru.job4j.pool;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ArrayFork extends RecursiveTask<Integer> {

    private int[] array;
    public static final int MAX_VALUE = 1000;
    private int countThreads;
    private ForkJoinPool forkJoinPool;
    private static final int MIN_ELEMENTS_TO_PARALLEL = 10;
    private int elementSearch;

    public ArrayFork(int[] array, int elementSearch) {
        this.array = array;
        countThreads = array.length > MIN_ELEMENTS_TO_PARALLEL ? Runtime.getRuntime().availableProcessors() : 1;
        this.elementSearch = elementSearch;
    }

    public ArrayFork(int[] array) {
        forkJoinPool = new ForkJoinPool();
        countThreads = array.length > MIN_ELEMENTS_TO_PARALLEL ? Runtime.getRuntime().availableProcessors() : 1;
        this.array = array;
    }


    public int findIndex(int elementSearch) {
        this.elementSearch = elementSearch;
        return forkJoinPool.invoke(new ArrayFork(array));
    }

    @Override
    protected Integer compute() {
        int countElements = array.length / countThreads;
        int[][] partArray = new int[countElements][];
        for (int i = 0; i < countThreads - 1; i++) {
            partArray[i] = new int[countElements];
            for (int j = 0; j < countElements; j++) {
                partArray[i][j] = array[i * countThreads + j];
            }
        }
        partArray[countThreads - 1] = new int[array.length - (countThreads - 1) * countElements];
        ArrayFork[] arrayForks = new ArrayFork[countThreads];
        for (int i = 0; i < countThreads; i++) {
            arrayForks[i] = new ArrayFork(partArray[i], elementSearch);
            arrayForks[i].fork();
        }
        for (int i = 0; i < arrayForks.length; i++) {
            if (arrayForks[i].join() != null) {
                return arrayForks[i].join();
            }
        }
        throw new NoSuchElementException(String.format("Element %s was not find in array"));
    }
}
