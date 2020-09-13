package org.umutzafer.hackerrank.thecoinproblem;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;


import static java.util.stream.Collectors.joining;

class Result {

    /*
     * Complete the 'getWays' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. LONG_INTEGER_ARRAY c
     */

    public static long getWays(int n, List<Long> c) {
        // Write your code here
        Collections.sort(c);
        Long[][] ways = new Long[c.size()+1][n+1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= c.size(); j++) {
                ways[j][i]=getWay(i,c.subList(0,j),ways);
            }
        }

        return ways[c.size()][n];

    }

    public static long getWay(int n, List<Long> c, Long[][] ways){
        if(n==0)
            return 1;
        if(c.size()==0)
            return 0;
        int newCoin = c.get(c.size()-1).intValue();
        if(n-newCoin <0)
            return ways[c.size()-1][n];

        return ways[c.size()-1][n] +
                ways[c.size()][n-newCoin];
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int m = Integer.parseInt(firstMultipleInput[1]);

        List<Long> c = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Long::parseLong)
                .collect(toList());

        // Print the number of ways of making change for 'n' units using coins having the values given by 'c'

        long ways = Result.getWays(n, c);

        bufferedWriter.write(String.valueOf(ways));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
