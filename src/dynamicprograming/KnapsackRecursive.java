package dynamicprograming;

public class KnapsackRecursive {
    static int knapsack(int[] wt, int[] val, int W, int n) {
        // Base Case: No items or capacity left
        if (n == 0 || W == 0) return 0;

        // If current item's weight > capacity, skip it
        if (wt[n-1] > W) 
            return knapsack(wt, val, W, n-1);
        
        // Max of (taking item, skipping item)
        return Math.max(
            val[n-1] + knapsack(wt, val, W - wt[n-1], n-1),
            knapsack(wt, val, W, n-1)
        );
    }

    public static void main(String[] args) {
        int[] val = {60, 100, 120};
        int[] wt = {10, 20, 30};
        int W = 50;
        System.out.println("Max Value (Recursive): " + knapsack(wt, val, W, val.length));
    }
}