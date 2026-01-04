package dynamicprograming;

import java.util.Arrays;

public class KnapsackSpaceOptimized {
    static int knapsack(int[] wt, int[] val, int W) {
        int[] dp = new int[W+1];

        for (int i = 0; i < wt.length; i++) {
            for (int w = W; w >= wt[i]; w--) { // Traverse backwards
                dp[w] = Math.max(val[i] + dp[w - wt[i]], dp[w]);
                System.out.println(Arrays.toString(dp));
            }
        }
        return dp[W];
    }

    public static void main(String[] args) {
//        int[] val = {60, 100, 120};
//        int[] wt = {10, 20, 30};
        
        int[] val = {8, 5,3};
        int[] wt = {1, 2, 3};
        int W = 5;
        System.out.println("Max Value (Space-Optimized): " + knapsack(wt, val, W));
    }
}