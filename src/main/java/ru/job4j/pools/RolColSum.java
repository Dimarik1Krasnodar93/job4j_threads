package ru.job4j.pools;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {

    public static Sums[] sum(int[][] matrix) {
        Sums[]  sums = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            sums[i] = new Sums();
            sums[i].setColSum(sumColumn(matrix, i));
            sums[i].setRowSum(sumRow(matrix, i));
        }
        return sums;
    }

    public static Sums[] asyncSum(int[][] matrix) {
        Sums[] array = new Sums[matrix.length];
        for (int i = 0; i < array.length; i++) {
            try {
                array[i] = new Sums();
                array[i].setColSum(sumColumnAsync(matrix, i).get());
                array[i].setRowSum(sumRowAsync(matrix, i).get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return array;
    }

    private static int sumColumn(int[][] matrix, int iColumn) {
        int result = 0;
        for (int i = 0; i < matrix.length; i++) {
            result += matrix[i][iColumn];
        }
        return result;
    }

    private static int sumRow(int[][] matrix, int iRow) {
        int result = 0;
        for (int i = 0; i < matrix[iRow].length; i++) {
            result += matrix[iRow][i];
        }
        return result;
    }

    private static CompletableFuture<Integer> sumColumnAsync(int[][] matrix, int iColumn) {
        return CompletableFuture.supplyAsync(
                () -> sumColumn(matrix, iColumn));
    }

    private static CompletableFuture<Integer> sumRowAsync(int[][] matrix, int iColumn) {
        return CompletableFuture.supplyAsync(
                () -> sumRow(matrix, iColumn));
    }
}