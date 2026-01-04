package backtracking;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class PowerSumSolutions {

    // 1. Backtracking with Recursion
    public static int powerSumRecursion(int X, int N) {
        return helperRecursion(X, N, 1);
    }

    private static int helperRecursion(int total, int N, int start) {
        if (total == 0) {
            return 1;
        }
        if (total < 0) {
            return 0;
        }
        int ways = 0;
        int k = start;
        while (Math.pow(k, N) <= total) {
            ways += helperRecursion(total - (int) Math.pow(k, N), N, k + 1);
            k++;
        }
        return ways;
    }

    // 2. Dynamic Programming
    public static int powerSumDP(int X, int N) {
        int[] dp = new int[X + 1];
        dp[0] = 1;
        for (int i = 1; Math.pow(i, N) <= X; i++) {
            int power = (int) Math.pow(i, N);
            for (int j = X; j >= power; j--) {
                dp[j] += dp[j - power];
            }
        }
        return dp[X];
    }

    // 3. Memoization
    private static Map<String, Integer> memo = new HashMap<>();

    public static int powerSumMemoization(int X, int N) {
        memo.clear();
        return helperMemoization(X, N, 1);
    }

    private static int helperMemoization(int total, int N, int start) {
        String key = total + "," + start;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        if (total == 0) {
            return 1;
        }
        if (total < 0) {
            return 0;
        }
        int ways = 0;
        int k = start;
        while (Math.pow(k, N) <= total) {
            ways += helperMemoization(total - (int) Math.pow(k, N), N, k + 1);
            k++;
        }
        memo.put(key, ways);
        return ways;
    }

    // 4. Iterative Backtracking
    public static int powerSumIterative(int X, int N) {
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{X, 1});
        int ways = 0;
        while (!stack.isEmpty()) {
            int[] current = stack.pop();
            int total = current[0];
            int start = current[1];
            if (total == 0) {
                ways++;
                continue;
            }
            if (total < 0) {
                continue;
            }
            int k = start;
            while (Math.pow(k, N) <= total) {
                stack.push(new int[]{total - (int) Math.pow(k, N), k + 1});
                k++;
            }
        }
        return ways;
    }

    // 5. Mathematical Optimization
    public static int powerSumMath(int X, int N) {
        return helperMath(X, N, 1);
    }

    private static int helperMath(int total, int N, int start) {
        if (total == 0) {
            return 1;
        }
        if (total < 0 || Math.pow(start, N) > total) {
            return 0;
        }
        return helperMath(total - (int) Math.pow(start, N), N, start + 1) + helperMath(total, N, start + 1);
    }

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter the integer X and the power N:");
//        int X = scanner.nextInt();
//        int N = scanner.nextInt();
//        scanner.close();
//
//        System.out.println("1. Backtracking with Recursion: " + powerSumRecursion(X, N));
//        System.out.println("2. Dynamic Programming: " + powerSumDP(X, N));
//        System.out.println("3. Memoization: " + powerSumMemoization(X, N));
//        System.out.println("4. Iterative Backtracking: " + powerSumIterative(X, N));
//        System.out.println("5. Mathematical Optimization: " + powerSumMath(X, N));

        // Hard-coded test cases
        System.out.println("\nHard-coded Test Cases:");
        int[][] testCases = {
            {100, 2}, // Expected: 1
            {100, 3}, // Expected: 0
            {10, 2},   // Expected: 1
            {13, 2},   // Expected: 1
            {20, 2}    // Expected: 1
        };

        for (int[] testCase : testCases) {
            int testX = testCase[0];
            int testN = testCase[1];
            System.out.println("\nTest Case: X = " + testX + ", N = " + testN);
            System.out.println("1. Backtracking with Recursion: " + powerSumRecursion(testX, testN));
            System.out.println("2. Dynamic Programming: " + powerSumDP(testX, testN));
            System.out.println("3. Memoization: " + powerSumMemoization(testX, testN));
            System.out.println("4. Iterative Backtracking: " + powerSumIterative(testX, testN));
            System.out.println("5. Mathematical Optimization: " + powerSumMath(testX, testN));
        }
    }
}