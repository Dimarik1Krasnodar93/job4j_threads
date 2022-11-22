package ru.job4j.pools;

public class Sums {
    private int rowSum;
    private int colSum;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
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