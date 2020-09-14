package org.umutzafer.hackerrank.greedy.goodlandelectricity;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Solution {

    // Complete the pylons function below.
    static int pylons(int k, int[] arr) {
        int plantCount = 0;
        int arrived = 0;
        int mover = k - 1;
        while (arrived < arr.length) {
            int planted = -1;
            //looking forward for planting place
            for (int i = arrived + mover; i >= arrived; i--) {
                if (arr.length > i && Objects.equals(arr[i], 1)) {
                    planted = i;
                    break;
                }
            }
            //looking backward
            if (Objects.equals(planted, -1)) {
                for (int i = arrived - 1; i >= arrived - mover; i--) {
                    if (i >= 0 && Objects.equals(arr[i], 1)) {
                        planted = i;
                        break;
                    }
                }
            }
            //sorry :(
            if (Objects.equals(planted, -1)) {
                return -1;
            }
            //plant & rebase
            plantCount++;
            arrived = planted + k;
        }

        return plantCount;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int result = pylons(k, arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
