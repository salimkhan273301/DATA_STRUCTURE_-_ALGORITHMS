package generalproblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class FindLeaders {

    // 1. Brute Force Approach
    public static List<Integer> findLeadersBruteForce(int[] arr) {
        List<Integer> leaders = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            boolean isLeader = true;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] < arr[j]) {
                    isLeader = false;
                    break;
                }
            }
            if (isLeader) {
                leaders.add(arr[i]);
            }
        }
        return leaders;
    }

    // 2. Optimized Approach (Right to Left Traversal)
    public static List<Integer> findLeadersOptimized(int[] arr) {
        List<Integer> leaders = new ArrayList<>();
        int maxSoFar = Integer.MIN_VALUE;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] >= maxSoFar) {
                leaders.add(arr[i]);
                maxSoFar = arr[i];
            }
        }
        // Reverse to maintain original order
        List<Integer> result = new ArrayList<>();
        for (int i = leaders.size() - 1; i >= 0; i--) {
            result.add(leaders.get(i));
        }
        return result;
    }

    // 3. Using Stack
    public static List<Integer> findLeadersStack(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        for (int i = arr.length - 1; i >= 0; i--) {
            if (stack.isEmpty() || arr[i] >= stack.peek()) {
                stack.push(arr[i]);
            }
        }
        List<Integer> leaders = new ArrayList<>();
        while (!stack.isEmpty()) {
            leaders.add(stack.pop());
        }
        return leaders;
    }

    // 4. Reverse and Compare
    public static List<Integer> findLeadersReverse(int[] arr) {
        List<Integer> leaders = new ArrayList<>();
        int[] reversed = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            reversed[i] = arr[arr.length - 1 - i];
        }
        int maxSoFar = Integer.MIN_VALUE;
        for (int num : reversed) {
            if (num >= maxSoFar) {
                leaders.add(num);
                maxSoFar = num;
            }
        }
        // Reverse to maintain original order
        List<Integer> result = new ArrayList<>();
        for (int i = leaders.size() - 1; i >= 0; i--) {
            result.add(leaders.get(i));
        }
        return result;
    }

    // 5. In-place Marking
    public static List<Integer> findLeadersInPlace(int[] arr) {
        List<Integer> leaders = new ArrayList<>();
        int maxSoFar = Integer.MIN_VALUE;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] >= maxSoFar) {
                leaders.add(arr[i]);
                maxSoFar = arr[i];
            }
        }
        // Reverse to maintain original order
        List<Integer> result = new ArrayList<>();
        for (int i = leaders.size() - 1; i >= 0; i--) {
            result.add(leaders.get(i));
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] testCases = {
            {16, 17, 4, 3, 5, 2},
            {10, 4, 2, 4, 1},
            {5, 10, 20, 40},
            {30, 10, 10, 5},
            {1, 2, 3, 4, 5},
            {5, 4, 3, 2, 1},
            {1}
        };

        for (int[] testCase : testCases) {
            System.out.println("Input: " + Arrays.toString(testCase));
            System.out.println("Brute Force: " + findLeadersBruteForce(testCase.clone()));
            System.out.println("Optimized: " + findLeadersOptimized(testCase.clone()));
            System.out.println("Stack: " + findLeadersStack(testCase.clone()));
            System.out.println("Reverse: " + findLeadersReverse(testCase.clone()));
            System.out.println("In-place: " + findLeadersInPlace(testCase.clone()));
            System.out.println();
        }
    }
}
