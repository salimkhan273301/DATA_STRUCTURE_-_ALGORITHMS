package sliding_window;

import java.util.*;

public class ContinousSubArray {

    public long continuousSubarrays(int[] nums) {
        int n = nums.length;
        long count = 0;
        Deque<Integer> maxD = new LinkedList<>();
        Deque<Integer> minD = new LinkedList<>();
        int left = 0;
        for (int right = 0; right < n; right++) {
            int cur = nums[right];
            while (!maxD.isEmpty() && nums[maxD.peekLast()] < cur) maxD.pollLast();
            maxD.addLast(right);
            while (!minD.isEmpty() && nums[minD.peekLast()] > cur) minD.pollLast();
            minD.addLast(right);

            while (nums[maxD.peekFirst()] - nums[minD.peekFirst()] > 2) {
                if (maxD.peekFirst() == left) maxD.pollFirst();
                if (minD.peekFirst() == left) minD.pollFirst();
                left++;
            }
            count += (right - left + 1);
        }
        return count;
    }

    public long continuousSubarraysBrute(int[] nums) {
        int n = nums.length;
        long count = 0;
        for (int i = 0; i < n; i++) {
            int minV = Integer.MAX_VALUE, maxV = Integer.MIN_VALUE;
            for (int j = i; j < n; j++) {
                minV = Math.min(minV, nums[j]);
                maxV = Math.max(maxV, nums[j]);
                if (maxV - minV > 2) break;
                count++;
            }
        }
        return count;
    }
    
    public long bruteForceTripleLoop(int[] nums) {
        int n = nums.length;
        long count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                boolean isValid = true;
                for (int x = i; x <= j && isValid; x++) {
                    for (int y = x + 1; y <= j; y++) {
                        if (Math.abs(nums[x] - nums[y]) > 2) {
                            isValid = false;
                            break;
                        }
                    }
                }
                if (isValid) count++;
            }
        }
        return count;
    }
    
    public long treeMapApproach(int[] nums) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        long count = 0;
        int left = 0;

        for (int right = 0; right < nums.length; right++) {
            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);

            while (map.lastKey() - map.firstKey() > 2) {
                map.put(nums[left], map.get(nums[left]) - 1);
                if (map.get(nums[left]) == 0) map.remove(nums[left]);
                left++;
            }

            count += right - left + 1;
        }

        return count;
    }



    public static void main(String[] args) {
    	ContinousSubArray sol = new ContinousSubArray();
        int[][] tests = {
            {5,4,2,4},
            {1,2,3},
            {1,3,5,2,4}
        };
        for (int[] t : tests) {
            System.out.println("nums = " + Arrays.toString(t));
            System.out.println("Brute Force (Triple Loop): " + sol.bruteForceTripleLoop(t));  // 8
           // System.out.println("Brute Force (Min/Max):     " + sol.bruteForceMinMax(t));      // 8
            System.out.println("TreeMap Sliding Window:    " + sol.treeMapApproach(t));       // 8
          //  System.out.println("Frequency Window:          " + sol.frequencyWindow(t));       // 8
            System.out.println("-----");
        }
    }
}
