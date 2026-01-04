package dynamic_programing;

import java.util.*;

public class MinCostClimbingStairsSolutions {

    // -----------------------------------------------------------
    // Solution 1: Simple Recursion (Exponential, TLE for big inputs)
    // -----------------------------------------------------------
    // Time: O(2^n) | Space: O(n) (recursion stack)
    public static int minCostRecursion(int[] cost, int i) {
        if (i < 0) return 0;
        if (i == 0 || i == 1) return cost[i];
        return cost[i] + Math.min(minCostRecursion(cost, i - 1),
                                  minCostRecursion(cost, i - 2));
    }
    public static int minCostRecursionDriver(int[] cost) {
        int n = cost.length;
        return Math.min(minCostRecursion(cost, n - 1), minCostRecursion(cost, n - 2));
    }

    // -----------------------------------------------------------
    // Solution 2: Recursion + Memoization (Top-Down DP)
    // -----------------------------------------------------------
    // Time: O(n) | Space: O(n) (recursion + memo)
    public static int minCostMemo(int[] cost, int i, int[] memo) {
        if (i < 0) return 0;
        if (i == 0 || i == 1) return cost[i];
        if (memo[i] != -1) return memo[i];
        return memo[i] = cost[i] + Math.min(minCostMemo(cost, i - 1, memo),
                                            minCostMemo(cost, i - 2, memo));
    }
    public static int minCostMemoDriver(int[] cost) {
        int n = cost.length;
        int[] memo = new int[n];
        Arrays.fill(memo, -1);
        return Math.min(minCostMemo(cost, n - 1, memo), minCostMemo(cost, n - 2, memo));
    }

    // -----------------------------------------------------------
    // Solution 3: Tabulation (Bottom-Up DP)
    // -----------------------------------------------------------
    // Time: O(n) | Space: O(n)
    public static int minCostTabulation(int[] cost) {
        int n = cost.length;
        if (n == 0) return 0;
        if (n == 1) return cost[0];
        int[] dp = new int[n];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < n; i++) {
            dp[i] = cost[i] + Math.min(dp[i - 1], dp[i - 2]);
        }
        return Math.min(dp[n - 1], dp[n - 2]);
    }

    // -----------------------------------------------------------
    // Solution 4: Space Optimized DP (Two Variables)
    // -----------------------------------------------------------
    // Time: O(n) | Space: O(1)
    public static int minCostSpaceOptimized(int[] cost) {
        int n = cost.length;
        if (n == 0) return 0;
        if (n == 1) return cost[0];

        int first = cost[0], second = cost[1];
        for (int i = 2; i < n; i++) {
            int curr = cost[i] + Math.min(first, second);
            first = second;
            second = curr;
        }
        return Math.min(first, second);
    }

    // -----------------------------------------------------------
    // Solution 5: Tabulation with Extra Step (dp[n] = min cost to reach top)
    // -----------------------------------------------------------
    // Time: O(n) | Space: O(n)
    public static int minCostWithExtraStep(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1],
                             dp[i - 2] + cost[i - 2]);
        }
        return dp[n];
    }

    // -----------------------------------------------------------
    // Testing all solutions
    // -----------------------------------------------------------
    public static void main(String[] args) {
        int[] cost1 = {10, 15, 20};
        int[] cost2 = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};

        System.out.println("Test Case 1: [10,15,20]");
        System.out.println("Recursion: " + minCostRecursionDriver(cost1));
        System.out.println("Memoization: " + minCostMemoDriver(cost1));
        System.out.println("Tabulation: " + minCostTabulation(cost1));
        System.out.println("Space Optimized: " + minCostSpaceOptimized(cost1));
        System.out.println("With Extra Step: " + minCostWithExtraStep(cost1));

        System.out.println("\nTest Case 2: [1,100,1,1,1,100,1,1,100,1]");
        System.out.println("Recursion: " + minCostRecursionDriver(cost2));
        System.out.println("Memoization: " + minCostMemoDriver(cost2));
        System.out.println("Tabulation: " + minCostTabulation(cost2));
        System.out.println("Space Optimized: " + minCostSpaceOptimized(cost2));
        System.out.println("With Extra Step: " + minCostWithExtraStep(cost2));
    }
}
