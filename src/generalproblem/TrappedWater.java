package generalproblem;

public class TrappedWater {
    public static long getTrappedWater(long[] arr, int n) {
        if (n == 0) return 0;

        long[] leftMax = new long[n];
        long[] rightMax = new long[n];
        long waterTrapped = 0;

        leftMax[0] = arr[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], arr[i]);
        }

        rightMax[n - 1] = arr[n - 1];
        for (int j = n - 2; j >= 0; j--) {
            rightMax[j] = Math.max(rightMax[j + 1], arr[j]);
        }

        for (int k = 0; k < n; k++) {
            long trapped = Math.min(leftMax[k], rightMax[k]) - arr[k];
            if (trapped > 0) {
                waterTrapped += trapped;
            }
        }

        return waterTrapped;
    }

    public static void main(String[] args) {
        long[] arr = {8, 1, 8, 2, 4};
        int n = arr.length;
        System.out.println("Trapped Water: " + getTrappedWater(arr, n)); // Output: 9 ✅
    }
}
