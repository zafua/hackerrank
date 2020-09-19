package org.umutzafer.hackerrank.constructive.flippingthematrix;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import java.util.stream.IntStream;

public class Solution {

    // Complete the flippingMatrix function below.
    static int flippingMatrix(int[][] matrix) {
        int total = 0;

        int n = matrix.length/2;
        for (int i = 0; i < matrix.length/2; i++) {
            for (int j = 0; j < matrix.length/2; j++) {


                total += (long) Math.max(Math.max(matrix[i][j], matrix[i][2*n-1-j]),
                        Math.max(matrix[2*n-1-i][j], matrix[2*n-1-i][2*n-1-j]));
            }
        }

        return total;
    }

    public static int[] getColumn(int[][] array, int index){
        int[] column = new int[array[0].length];
        for(int i=0; i<column.length; i++){
            column[i] = array[i][index];
        }
        return column;
    }

    public static int[][] setColumn(int[] array, int[][] matrix, int index){
        for (int i = 0; i < array.length; i++) {
            matrix[i][index] = array[i];
        }
        return matrix;
    }

    private static int[] compareAndReverse(int[] matrix, boolean asc) {
        int subSize = matrix.length/2;
        int firstHalfSum = 0, lastHalfSum = 0;

        for (int i = 0; i < subSize; i++) {
            firstHalfSum+=matrix[i];
        }
        for (int i = subSize; i < matrix.length; i++) {
            lastHalfSum+=matrix[i];
        }

        if(firstHalfSum<=lastHalfSum && asc){
            return matrix;
        }

        if(firstHalfSum>=lastHalfSum && !asc){
            return matrix;
        }

        for(int i = 0; i < matrix.length / 2; i++)
        {
            int temp = matrix[i];
            matrix[i] = matrix[matrix.length - i - 1];
            matrix[matrix.length - i - 1] = temp;
        }

        return matrix;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        int[][] matrix =
                {{107, 54, 128, 15},
                    {12, 75, 110, 138},
                    {100, 96, 34, 85},
                    {75, 15, 28, 112}};
        int result = flippingMatrix(matrix);

    }
}
