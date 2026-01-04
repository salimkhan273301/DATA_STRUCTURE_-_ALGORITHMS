package dynamic_programing;

import java.util.Arrays;
/*
	    (0)
     /       \
     Rob     Skip
    /         \
   (2)           (1)
   /   \         /   \
 (4)     (3)    (3)     (2)
/  \     / \    / \     / \
(6) (5) (5) (4)  (4)(3)  (4)(3)

*/

public class HouseRobber {

    // ---------- Solution 1: Pure Recursion (Brute Force) ----------
    public int robRecursive(int[] nums) {
        return robFromRecursive(0, nums);
    }

    private int robFromRecursive(int i, int[] nums) {
        if (i >= nums.length) return 0; // Base case: beyond the last house

        // Either rob current house and skip next, or skip current
        int rob = nums[i] + robFromRecursive(i + 2, nums); // Rob current
        int skip = robFromRecursive(i + 1, nums);          // Skip current

        return Math.max(rob, skip); // Choose the max
    }

    // ---------- Solution 2: Top-down Memoization ----------
    public int robMemo(int[] nums) {
        int[] memo = new int[nums.length];
        Arrays.fill(memo, -1); // Initialize memo with -1 (not calculated)
        return robFromMemo(0, nums, memo);
    }

    private int robFromMemo(int i, int[] nums, int[] memo) {
        if (i >= nums.length) return 0;
        if (memo[i] != -1) return memo[i]; // Use cached value if available

        int rob = nums[i] + robFromMemo(i + 2, nums, memo);
        int skip = robFromMemo(i + 1, nums, memo);

        memo[i] = Math.max(rob, skip); // Save result in memo
        return memo[i];
    }

    // ---------- Solution 3: Bottom-up Dynamic Programming ----------
    public int robBottomUp(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];

        int[] dp = new int[n]; // dp[i] = max money from house i to end
        dp[n - 1] = nums[n - 1]; // Only one house left
        dp[n - 2] = Math.max(nums[n - 2], nums[n - 1]); // Max of last two houses

        // Fill DP table from right to left
        for (int i = n - 3; i >= 0; i--) {
            dp[i] = Math.max(nums[i] + dp[i + 2], dp[i + 1]); // Choose rob or skip
        }

        return dp[0]; // Max money from house 0
    }

    // ---------- Solution 4: Optimized DP using Two Variables ----------
    public int robOptimized(int[] nums) {
        int robNextPlusOne = 0; // dp[i+2]
        int robNext = 0;        // dp[i+1]

        for (int i = nums.length - 1; i >= 0; i--) {
            int current = Math.max(nums[i] + robNextPlusOne, robNext);
            robNextPlusOne = robNext;
            robNext = current;
        }

        return robNext;
    }

    // ---------- Solution 5: Rolling Array DP ----------
    public int robRollingArray(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];

        int[] dp = new int[3]; // Rolling array of size 3
        dp[(n - 1) % 3] = nums[n - 1]; // Last house
        dp[(n - 2) % 3] = Math.max(nums[n - 2], nums[n - 1]);

        for (int i = n - 3; i >= 0; i--) {
            dp[i % 3] = Math.max(nums[i] + dp[(i + 2) % 3], dp[(i + 1) % 3]);
        }

        return dp[0];
    }

    // ---------- Main method to test all solutions ----------
    public static void main(String[] args) {
        HouseRobber hr = new HouseRobber();

        int[][] testCases = {
            {2, 7, 9, 3, 1},
            {1, 2, 3, 1},
            {2, 1, 1, 2},
            {2, 1},
            {1},
            {}
        };

        for (int[] nums : testCases) {
            System.out.println("------------------------------------------------");
            System.out.println("Input: " + Arrays.toString(nums));

            System.out.println("1. Recursion:        " + hr.robRecursive(nums));
            System.out.println("2. Memoization:      " + hr.robMemo(nums));
            System.out.println("3. Bottom-Up DP:     " + hr.robBottomUp(nums));
            System.out.println("4. Optimized DP:     " + hr.robOptimized(nums));
            System.out.println("5. Rolling Array DP: " + hr.robRollingArray(nums));
        }
    }
}
