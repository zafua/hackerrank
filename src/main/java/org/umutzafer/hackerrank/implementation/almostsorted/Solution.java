package org.umutzafer.hackerrank.implementation.almostsorted;

import java.util.*;

public class Solution {

    // Hackerrank
    // https://www.hackerrank.com/challenges/almost-sorted/problem
    // Complete the almostSorted function below.
    static void almostSorted(int[] arr) {
        int[] sorted = arr.clone();
        Arrays.sort(sorted);
        int startIndex = 0;
        int endIndex = 0;
        if(isSorted(arr, sorted)){
            System.out.println("yes");
            return;
        }

        for (int i = 0; i < arr.length-1; i++) {
            if(arr[i]>arr[i+1]){
                startIndex = i;
                break;
            }
        }

        for (int i = arr.length-1; i >0 ; i--) {
            if(arr[i]<arr[i-1]){
                endIndex = i;
                break;
            }
        }

        //try swap
        int[] swapped = arr.clone();
        int temp = swapped[startIndex];
        swapped[startIndex] = swapped[endIndex];
        swapped[endIndex] = temp;
        if(isSorted(swapped, sorted)){
            System.out.println("yes");
            System.out.println("swap "+ startIndex + " " + endIndex);
            return;
        }

        //try reverse
        for(int i = startIndex; i < ((endIndex+1)-startIndex)/2+startIndex; i++)
        {
            int tempReverse = arr[i];
            arr[i] = arr[endIndex - (i-startIndex)];
            arr[endIndex - (i-startIndex)] = tempReverse;
        }
        if(isSorted(arr, sorted)){
            System.out.println("yes");
            startIndex++;
            endIndex++;
            System.out.println("reverse "+ startIndex + " " + endIndex);
            return;
        }

        System.out.println("no");

    }

    static boolean isSorted(int[] arr, int[] sorted){
        for (int i = 0; i < sorted.length; i++) {
            if(!Objects.equals(arr[i], sorted[i])){
                return false;
            }
        }
        return true;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        almostSorted(arr);

        scanner.close();
    }

}
