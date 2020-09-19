package org.umutzafer.hackerrank.implementation.matrixlayerrotation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Solution {

    // Complete the matrixRotation function below.
    // Hackerrank
    // https://www.hackerrank.com/challenges/matrix-rotation-algo/problem
    static void matrixRotation(List<List<Integer>> matrix, int r) {
        int rowSize = matrix.size();
        int colSize = matrix.get(0).size();
        List<List<Integer>> subLists = new ArrayList<>();
        List<List<Integer>> rotatedLists = new ArrayList<>();

        for (int i = 0; i < Math.min(colSize,rowSize)/2; i++) {

            //sublist oluşturmak
            List<Integer> someList = new LinkedList<>();
            for (int j = i+1; j < colSize-i; j++) {
                someList.add(matrix.get(i).get(j));
            }
            for (int j = i+1; j <rowSize-i; j++) {
                someList.add(matrix.get(j).get((colSize-i)-1));
            }
            for (int j = colSize-2-i; j >= i ; j--) {
                someList.add((matrix.get((rowSize-i)-1).get(j)));
            }
            for (int j = rowSize-2-i; j >= i ; j--) {
                someList.add(matrix.get(j).get(i));
            }

            subLists.add(someList);
        }

        //sublistleri rotate etmek
        for (List<Integer> currentList : subLists) {
            List<Integer> rotated = new ArrayList<>();
            int newR = r % currentList.size();
            for (int j = 0; j < currentList.size(); j++) {
                rotated.add(currentList.get((newR + j) % currentList.size()));
            }
            rotatedLists.add(rotated);
        }

        //matrix'i tekrar oluştur
        int[][] finalArray = new int[rowSize][colSize];
        for (int i = 0; i < Math.min(colSize,rowSize)/2; i++) {
            List<Integer> list = rotatedLists.get(i);
            int counter = 0;
            for (int j = i+1; j <colSize-i ; j++) {
                finalArray[i][j] = list.get(counter);
                counter++;
            }
            counter = 0;
            for (int j = i+1; j <rowSize-i ; j++) {
                finalArray[j][(colSize-i)-1] = list.get(((colSize-(i*2))-1)+counter);
                counter++;
            }
            counter = 0;
            for (int j = colSize-2-i; j >= i ; j--) {
                finalArray[(rowSize-i)-1][j] = list.get(((colSize-(i*2))-1)+((rowSize-(i*2))-1)+counter);
                counter++;
            }
            counter = 0;
            for (int j = rowSize-2-i; j >= i ; j--) {
                finalArray[j][i] = list.get(((rowSize-(i*2))-1)+(2*((colSize-(i*2))-1))+counter);
                counter++;
            }

        }

        for(int i=0;i<rowSize;i++){
            for(int j=0;j<colSize;j++)
                System.out.print(finalArray[i][j]+" ");
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] mnr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int m = Integer.parseInt(mnr[0]);

        int n = Integer.parseInt(mnr[1]);

        int r = Integer.parseInt(mnr[2]);

        List<List<Integer>> matrix = new ArrayList<>();

        IntStream.range(0, m).forEach(i -> {
            try {
                matrix.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        matrixRotation(matrix, r);

        bufferedReader.close();
    }
}
