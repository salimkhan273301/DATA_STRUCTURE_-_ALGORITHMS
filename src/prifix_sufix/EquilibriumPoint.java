package prifix_sufix;

import java.util.*;

public class EquilibriumPoint {

    /***************** Solution 1: Prefix Sum (Your Code) *****************
     * Idea: Total sum - prefix - arr[i] = suffix
     * Time: O(N)
     * Space: O(1)
     *********************************************************************/
    public static int equilibriumPrefix(int[] arr) {
        int total = 0, prefix = 0;
        for (int e : arr) total += e;
        for (int i = 0; i < arr.length; i++) {
            int suffix = total - prefix - arr[i];
            if (prefix == suffix) return i;
            prefix += arr[i];
        }
        return -1;
    }

    /***************** Solution 2: Two Pointers *****************
     * Idea: Start from both ends, maintain leftSum and rightSum.
     * Time: O(N)
     * Space: O(1)
     *********************************************************************/
    public static int equilibriumTwoPointers(int[] arr) {
        int left = 0, right = arr.length - 1;
        int leftSum = 0, rightSum = 0;
        while (left <= right) {
            if (leftSum < rightSum) leftSum += arr[left++];
            else if (rightSum < leftSum) rightSum += arr[right--];
            else {
                if (left == right) return left;
                leftSum += arr[left++];
                rightSum += arr[right--];
            }
        }
        return -1;
    }

    /***************** Solution 3: Prefix + HashMap *****************
     * Store prefix sums in HashMap, then check suffix.
     * Time: O(N)
     * Space: O(N)
     *********************************************************************/
    public static int equilibriumHashMap(int[] arr) {
        int n = arr.length;
        int total = 0;
        for (int e : arr) total += e;
        Map<Integer, Integer> prefixMap = new HashMap<>();
        int prefix = 0;
        for (int i = 0; i < n; i++) {
            prefixMap.put(i, prefix);
            prefix += arr[i];
        }
        int suffix = 0;
        for (int i = n - 1; i >= 0; i--) {
            suffix = total - arr[i] - prefixMap.get(i) - suffix;
            if (prefixMap.get(i) == total - arr[i] - prefixMap.get(i)) return i;
        }
        return -1;
    }

    /***************** Solution 4: Brute Force *****************
     * For each index compute left sum and right sum separately.
     * Time: O(N^2)
     * Space: O(1)
     *********************************************************************/
    public static int equilibriumBruteForce(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int left = 0, right = 0;
            for (int j = 0; j < i; j++) left += arr[j];
            for (int j = i + 1; j < n; j++) right += arr[j];
            if (left == right) return i;
        }
        return -1;
    }

    /***************** Solution 5: Prefix Array *****************
     * Precompute prefix sum and suffix sum arrays.
     * Time: O(N)
     * Space: O(N)
     *********************************************************************/
    public static int equilibriumPrefixSuffixArrays(int[] arr) {
        int n = arr.length;
        int[] prefix = new int[n];
        int[] suffix = new int[n];
        prefix[0] = arr[0];
        for (int i = 1; i < n; i++) prefix[i] = prefix[i - 1] + arr[i];
        suffix[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) suffix[i] = suffix[i + 1] + arr[i];
        for (int i = 0; i < n; i++) {
            int left = (i == 0) ? 0 : prefix[i - 1];
            int right = (i == n - 1) ? 0 : suffix[i + 1];
            if (left == right) return i;
        }
        return -1;
    }

    /***************** Solution 6: Running Balance *****************
     * Trick: Keep subtracting arr[i] from total, compare with left sum.
     * Time: O(N)
     * Space: O(1)
     *********************************************************************/
    public static int equilibriumRunningBalance(int[] arr) {
        int total = 0, leftSum = 0;
        for (int e : arr) total += e;
        for (int i = 0; i < arr.length; i++) {
            total -= arr[i];
            if (total == leftSum) return i;
            leftSum += arr[i];
        }
        return -1;
    }

    /***************** Solution 7: Divide & Conquer *****************
     * Recursively check mid point balance.
     * Time: O(N log N)
     * Space: O(log N) recursion
     *********************************************************************/
    public static int equilibriumDivideConquer(int[] arr) {
        return equilibriumDC(arr, 0, arr.length - 1);
    }

    private static int equilibriumDC(int[] arr, int l, int r) {
        if (l > r) return -1;
        int mid = (l + r) / 2;
        int left = 0, right = 0;
        for (int i = l; i < mid; i++) left += arr[i];
        for (int i = mid + 1; i <= r; i++) right += arr[i];
        if (left == right) return mid;
        if (left > right) return equilibriumDC(arr, l, mid - 1);
        else return equilibriumDC(arr, mid + 1, r);
    }

    /***************** Solution 8: Recursive *****************
     * Check each index recursively.
     * Time: O(N^2)
     * Space: O(N) recursion
     *********************************************************************/
    public static int equilibriumRecursive(int[] arr) {
        return equilibriumRecursiveHelper(arr, 0);
    }

    private static int equilibriumRecursiveHelper(int[] arr, int idx) {
        if (idx == arr.length) return -1;
        int left = 0, right = 0;
        for (int i = 0; i < idx; i++) left += arr[i];
        for (int i = idx + 1; i < arr.length; i++) right += arr[i];
        if (left == right) return idx;
        return equilibriumRecursiveHelper(arr, idx + 1);
    }

    /***************** Solution 9: Functional Stream *****************
     * Java Streams for fancy prefix/suffix computation.
     * Time: O(N^2) due to sublist sums
     * Space: O(1) (except stream overhead)
     *********************************************************************/
    public static int equilibriumStreams(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int left = Arrays.stream(arr, 0, i).sum();
            int right = Arrays.stream(arr, i + 1, arr.length).sum();
            if (left == right) return i;
        }
        return -1;
    }

    /***************** Solution 10: Cumulative Difference *****************
     * Keep cumulative difference of leftSum - rightSum.
     * Time: O(N)
     * Space: O(1)
     *********************************************************************/
    public static int equilibriumDifferenceMethod(int[] arr) {
        int total = Arrays.stream(arr).sum();
        int leftSum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (2 * leftSum + arr[i] == total) return i;
            leftSum += arr[i];
        }
        return -1;
    }

    /***************** Test Cases *****************/
    public static void main(String[] args) {
        int[][] tests = {
            {1, 3, 5, 2, 2},   // expected 2
            {2, 4, 6},         // expected -1
            {0, 0, 0, 0},      // expected 0
            {10, -10, 10, -10, 10, -10, 10}, // expected 6
            {1},               // expected 0
            {1, 2, 3},         // expected -1
        };

        for (int[] test : tests) {
            System.out.println("Array: " + Arrays.toString(test));
            System.out.println("Prefix Method → " + equilibriumPrefix(test));
            System.out.println("Two Pointers  → " + equilibriumTwoPointers(test));
            System.out.println("HashMap       → " + equilibriumHashMap(test));
            System.out.println("Brute Force   → " + equilibriumBruteForce(test));
            System.out.println("Prefix/Suffix → " + equilibriumPrefixSuffixArrays(test));
            System.out.println("RunningBal    → " + equilibriumRunningBalance(test));
            System.out.println("Divide&Conq   → " + equilibriumDivideConquer(test));
            System.out.println("Recursive     → " + equilibriumRecursive(test));
            System.out.println("Streams       → " + equilibriumStreams(test));
            System.out.println("Diff Method   → " + equilibriumDifferenceMethod(test));
            System.out.println("--------------------------------------------------");
        }
    }
}
