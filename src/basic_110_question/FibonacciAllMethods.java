package basic_110_question;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class FibonacciAllMethods {

    // 1️⃣ Iterative Approach (Best for printing series)
    public static void fibonacciIterative(int n) {
        int first = 0, second = 1;

        System.out.print("Iterative: ");
        for (int i = 0; i < n; i++) {
            System.out.print(first + " ");
            int next = first + second;
            first = second;
            second = next;
        }
        System.out.println();
    }

    // 2️⃣ Simple Recursion (Not efficient)
    public static int fibonacciRecursive(int n) {
        if (n <= 1) return n;
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    // 3️⃣ Recursion + Memoization (Top-Down DP)
    static Map<Integer, Integer> memo = new HashMap<>();

    public static int fibonacciMemo(int n) {
        if (n <= 1) return n;

        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        int value = fibonacciMemo(n - 1) + fibonacciMemo(n - 2);
        memo.put(n, value);
        return value;
    }

    // 4️⃣ Tabulation (Bottom-Up DP)
    public static int fibonacciTabulation(int n) {
        if (n <= 1) return n;

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    // 5️⃣ Space Optimized DP (Best for single nth value)
    public static int fibonacciOptimized(int n) {
        if (n <= 1) return n;

        int a = 0, b = 1;

        for (int i = 2; i <= n; i++) {
            int c = a + b;
            a = b;
            b = c;
        }

        return b;
    }

    // 6️⃣ Java 8 Stream
    public static void fibonacciStream(int n) {
        System.out.print("Stream: ");
        Stream.iterate(new int[]{0, 1}, f -> new int[]{f[1], f[0] + f[1]})
                .limit(n)
                .forEach(f -> System.out.print(f[0] + " "));
        System.out.println();
    }

    // 7️⃣ Binet’s Formula (Mathematical Formula)
    public static int fibonacciFormula(int n) {
        double phi = (1 + Math.sqrt(5)) / 2;
        return (int) Math.round(Math.pow(phi, n) / Math.sqrt(5));
    }

    public static void main(String[] args) {

        int n = 10;

        System.out.println("----- Fibonacci Series -----");

        // 1 Iterative
        fibonacciIterative(n);

        // 2 Recursive
        System.out.print("Recursive: ");
        for (int i = 0; i < n; i++) {
            System.out.print(fibonacciRecursive(i) + " ");
        }
        System.out.println();

        // 3 Memoization
        System.out.print("Memoization: ");
        for (int i = 0; i < n; i++) {
            System.out.print(fibonacciMemo(i) + " ");
        }
        System.out.println();

        // 4 Tabulation
        System.out.print("Tabulation: ");
        for (int i = 0; i < n; i++) {
            System.out.print(fibonacciTabulation(i) + " ");
        }
        System.out.println();

        // 5 Space Optimized
        System.out.print("Optimized DP: ");
        for (int i = 0; i < n; i++) {
            System.out.print(fibonacciOptimized(i) + " ");
        }
        System.out.println();

        // 6 Stream
        fibonacciStream(n);

        // 7 Formula
        System.out.print("Formula: ");
        for (int i = 0; i < n; i++) {
            System.out.print(fibonacciFormula(i) + " ");
        }
    }
}