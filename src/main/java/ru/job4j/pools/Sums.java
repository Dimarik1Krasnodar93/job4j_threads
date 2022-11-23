package ru.job4j.pools;

import java.util.Objects;

public class Sums {
    private int rowSum;
    private int colSum;

    @Override
    public int hashCode() {
        return Objects.hash(rowSum, colSum);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Sums sums = (Sums) obj;
        return rowSum == sums.getRowSum() && colSum == sums.getColSum();
    }

    public int getRowSum() {
        return rowSum;
    }

    public void setRowSum(int rowSum) {
        this.rowSum = rowSum;
    }

    public int getColSum() {
        return colSum;
    }

    public void setColSum(int colSum) {
        this.colSum = colSum;
    }
}