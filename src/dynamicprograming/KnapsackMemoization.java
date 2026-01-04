package dynamicprograming;

import java.util.Arrays;

public class KnapsackMemoization {
    static int knapsack(int[] wt, int[] val, int W, int n, int[][] dp) {
        if (n == 0 || W == 0) return 0;
        if (dp[n][W] != -1) return dp[n][W]; // Return cached result

        if (wt[n-1] > W) 
            return dp[n][W] = knapsack(wt, val, W, n-1, dp);
        
        return dp[n][W] = Math.max(
            val[n-1] + knapsack(wt, val, W - wt[n-1], n-1, dp),
            knapsack(wt, val, W, n-1, dp)
        );
    }

    public static void main(String[] args) {
        int[] val = {60, 100, 120};
        int[] wt = {10, 20, 30};
        int W = 50;
        int[][] dp = new int[val.length+1][W+1];
        for (int[] row : dp) Arrays.fill(row, -1); // Initialize with -1

        System.out.println("Max Value (Memoization): " + knapsack(wt, val, W, val.length, dp));
    }
}