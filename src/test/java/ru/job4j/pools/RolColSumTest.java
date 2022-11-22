package ru.job4j.pools;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class RolColSumTest {
    private static int[][] matrix1;
    private static int[][] matrix2;



    @Test
    public void testMatrix2x2() {
        Sums[] sumsExpected1;
        matrix1 = new int[2][];
        matrix1[0] = new int[2];
        matrix1[1] = new int[2];
        matrix1[0][0] = 1;
        matrix1[0][1] = 2;
        matrix1[1][0] = 3;
        matrix1[1][1] = 4;
        sumsExpected1 = new Sums[2];
        sumsExpected1[0] = new Sums();
        sumsExpected1[1] = new Sums();
        sumsExpected1[0].setRowSum(3);
        sumsExpected1[1].setRowSum(7);
        sumsExpected1[0].setColSum(4);
        sumsExpected1[1].setColSum(6);
        Sums[] sums = RolColSum.sum(matrix1);
        assertThat(sums).isEqualTo(sumsExpected1);
        sums = RolColSum.asyncSum(matrix1);
        assertThat(sums).isEqualTo(sumsExpected1);
    }

    @Test
    public void testMatrix3x3() {
        Sums[] sumsExpected2;
        matrix2 = new int[3][];
        matrix2[0] = new int[3];
        matrix2[1] = new int[3];
        matrix2[2] = new int[3];
        matrix2[0][0] = 1;
        matrix2[0][1] = 2;
        matrix2[0][2] = 0;
        matrix2[1][0] = 3;
        matrix2[1][1] = 4;
        matrix2[1][2] = 0;
        matrix2[2][0] = 0;
        matrix2[2][1] = 0;
        matrix2[2][2] = 1;
        sumsExpected2 = new Sums[3];
        sumsExpected2[0] = new Sums();
        sumsExpected2[1] = new Sums();
        sumsExpected2[2] = new Sums();
        sumsExpected2[0].setRowSum(3);
        sumsExpected2[1].setRowSum(7);
        sumsExpected2[2].setRowSum(1);
        sumsExpected2[0].setColSum(4);
        sumsExpected2[1].setColSum(6);
        sumsExpected2[2].setColSum(1);
        Sums[] sums = RolColSum.sum(matrix2);
        assertThat(sums).isEqualTo(sumsExpected2);
        sums = RolColSum.asyncSum(matrix2);
        assertThat(sums).isEqualTo(sumsExpected2);
    }

}
