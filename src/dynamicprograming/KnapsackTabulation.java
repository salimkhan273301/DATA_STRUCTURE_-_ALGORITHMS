package dynamicprograming;

public class KnapsackTabulation {
    static int knapsack(int[] wt, int[] val, int W) {
        int n = val.length;
        int[][] dp = new int[n+1][W+1];

        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= W; w++) {
                if (wt[i-1] <= w) 
                    dp[i][w] = Math.max(val[i-1] + dp[i-1][w - wt[i-1]], dp[i-1][w]);
                else 
                    dp[i][w] = dp[i-1][w];
            }
        }
        return dp[n][W];
    }

    public static void main(String[] args) {
        int[] val = {60, 100, 120};
        int[] wt = {10, 20, 30};
        int W = 50;
        System.out.println("Max Value (Tabulation): " + knapsack(wt, val, W));
    }
}