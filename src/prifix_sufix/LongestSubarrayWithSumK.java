package prifix_sufix;

import java.util.*;

public class LongestSubarrayWithSumK {

    /***************** Solution 1: HashMap + Prefix Sum (Optimal) *****************
     * Idea: Maintain prefix sum and earliest index. 
     * Time: O(N)
     * Space: O(N)
     *********************************************************************/
    public static int longestSubarrayHashMap(int[] arr, int k) {
        int res = 0, pSum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            pSum += arr[i];
            if (pSum == k) res = i + 1;
            if (map.containsKey(pSum - k)) {
                res = Math.max(res, i - map.get(pSum - k));
            }
            map.putIfAbsent(pSum, i);
        }
        return res;
    }

    /***************** Solution 2: Sliding Window (only positives) *****************
     * Works when array has only non-negative numbers.
     * Time: O(N)
     * Space: O(1)
     *********************************************************************/
    public static int longestSubarraySlidingWindow(int[] arr, int k) {
        int start = 0, sum = 0, res = 0;
        for (int end = 0; end < arr.length; end++) {
            sum += arr[end];
            while (sum > k && start <= end) sum -= arr[start++];
            if (sum == k) res = Math.max(res, end - start + 1);
        }
        return res;
    }

    /***************** Solution 3: Brute Force *****************
     * Check every subarray sum.
     * Time: O(N^2)
     * Space: O(1)
     *********************************************************************/
    public static int longestSubarrayBruteForce(int[] arr, int k) {
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                if (sum == k) res = Math.max(res, j - i + 1);
            }
        }
        return res;
    }

    /***************** Solution 4: Prefix Array *****************
     * Store prefix sums in array and search.
     * Time: O(N^2)
     * Space: O(N)
     *********************************************************************/
    public static int longestSubarrayPrefixArray(int[] arr, int k) {
        int n = arr.length;
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) prefix[i + 1] = prefix[i] + arr[i];
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (prefix[j] - prefix[i] == k) res = Math.max(res, j - i);
            }
        }
        return res;
    }

    /***************** Solution 5: Binary Search on Prefix (Sorted Prefix Sums) *****************
     * Only works if array has positive integers.
     * Time: O(N log N)
     * Space: O(N)
     *********************************************************************/
    public static int longestSubarrayBinarySearch(int[] arr, int k) {
        int n = arr.length;
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) prefix[i + 1] = prefix[i] + arr[i];
        int res = 0;
        for (int i = 0; i < n; i++) {
            int target = k + prefix[i];
            int j = Arrays.binarySearch(prefix, target);
            if (j > i) res = Math.max(res, j - i);
        }
        return res;
    }

    /***************** Solution 6: Recursion *****************
     * Check subarrays recursively.
     * Time: O(N^2)
     * Space: O(N) recursion
     *********************************************************************/
    public static int longestSubarrayRecursive(int[] arr, int k) {
        return helper(arr, k, 0, 0, 0);
    }
    private static int helper(int[] arr, int k, int idx, int sum, int len) {
        if (idx == arr.length) return (sum == k) ? len : 0;
        int take = helper(arr, k, idx + 1, sum + arr[idx], len + 1);
        int skip = helper(arr, k, idx + 1, 0, 0);
        return Math.max(take, skip);
    }

    /***************** Solution 7: Divide & Conquer *****************
     * Split array and search.
     * Time: O(N log N)
     * Space: O(log N) recursion
     *********************************************************************/
    public static int longestSubarrayDivideConquer(int[] arr, int k) {
        return divideConquer(arr, 0, arr.length - 1, k);
    }
    private static int divideConquer(int[] arr, int l, int r, int k) {
        if (l > r) return 0;
        if (l == r) return (arr[l] == k) ? 1 : 0;
        int mid = (l + r) / 2;
        int left = divideConquer(arr, l, mid, k);
        int right = divideConquer(arr, mid + 1, r, k);
        int cross = crossSum(arr, l, mid, r, k);
        return Math.max(left, Math.max(right, cross));
    }
    private static int crossSum(int[] arr, int l, int m, int r, int k) {
        int leftSum = 0, maxLeft = 0;
        for (int i = m; i >= l; i--) {
            leftSum += arr[i];
            if (leftSum == k) maxLeft = m - i + 1;
        }
        int rightSum = 0, maxRight = 0;
        for (int j = m + 1; j <= r; j++) {
            rightSum += arr[j];
            if (rightSum == k) maxRight = j - m;
        }
        return maxLeft + maxRight;
    }

    /***************** Solution 8: Streams *****************
     * Use Java Streams (inefficient).
     * Time: O(N^2)
     * Space: O(1)
     *********************************************************************/
    public static int longestSubarrayStreams(int[] arr, int k) {
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j <= arr.length; j++) {
                int sum = Arrays.stream(arr, i, j).sum();
                if (sum == k) res = Math.max(res, j - i);
            }
        }
        return res;
    }

    /***************** Solution 9: Difference Formula *****************
     * Use prefix sum difference check with map.
     * Time: O(N)
     * Space: O(N)
     *********************************************************************/
    public static int longestSubarrayDifferenceMethod(int[] arr, int k) {
        int total = 0, res = 0;
        Map<Integer, Integer> firstIndex = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            total += arr[i];
            if (total == k) res = i + 1;
            if (firstIndex.containsKey(total - k))
                res = Math.max(res, i - firstIndex.get(total - k));
            firstIndex.putIfAbsent(total, i);
        }
        return res;
    }

    /***************** Solution 10: Dynamic Programming *****************
     * Not optimal but illustrative.
     * Time: O(N^2)
     * Space: O(N^2)
     *********************************************************************/
    public static int longestSubarrayDP(int[] arr, int k) {
        int n = arr.length, res = 0;
        int[][] dp = new int[n][n]; 
        for (int i = 0; i < n; i++) {
            dp[i][i] = arr[i];
            if (arr[i] == k) res = Math.max(res, 1);
        }
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                dp[i][j] = dp[i][j - 1] + arr[j];
                if (dp[i][j] == k) res = Math.max(res, len);
            }
        }
        return res;
    }

    /***************** Test Cases *****************/
    public static void main(String[] args) {
        int[][] tests = {
            {10, 5, 2, 7, 1, 9}, // expected longest = 4 (5+2+7+1=15)
            {1, 2, 3, 4, 5},    // expected 2 (2+3=5)
            {1, -1, 5, -2, 3},  // expected 4 (whole array sum 6)
            {-2, -1, 2, 1},     // expected 2 (2+1=3)
            {3, 1, 0, 1, 8, 2, 3, 6}, // expected 4 (0+1+8+2=11)
        };
        int k = 15;
        for (int[] test : tests) {
            System.out.println("Array: " + Arrays.toString(test));
            System.out.println("HashMap      → " + longestSubarrayHashMap(test, k));
            System.out.println("Sliding Win  → " + longestSubarraySlidingWindow(test, k));
            System.out.println("Brute Force  → " + longestSubarrayBruteForce(test, k));
            System.out.println("Prefix Array → " + longestSubarrayPrefixArray(test, k));
            System.out.println("BinarySearch → " + longestSubarrayBinarySearch(test, k));
            System.out.println("Recursive    → " + longestSubarrayRecursive(test, k));
            System.out.println("DivConquer   → " + longestSubarrayDivideConquer(test, k));
            System.out.println("Streams      → " + longestSubarrayStreams(test, k));
            System.out.println("Diff Method  → " + longestSubarrayDifferenceMethod(test, k));
            System.out.println("DP           → " + longestSubarrayDP(test, k));
            System.out.println("--------------------------------------------------");
        }
    }
}
