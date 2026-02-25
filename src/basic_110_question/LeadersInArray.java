package basic_110_question;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LeadersInArray {
    
    // Solution 1: Scan from Right (Optimized)
    public static List<Integer> findLeadersOptimized(int[] arr) {
        List<Integer> leaders = new ArrayList<>();
        
        if (arr.length == 0) return leaders;
        
        int maxFromRight = arr[arr.length - 1];
        leaders.add(maxFromRight);
        
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] > maxFromRight) {
                maxFromRight = arr[i];
                leaders.add(arr[i]);
            }
        }
        
        Collections.reverse(leaders);
        return leaders;
    }
    
    // Solution 2: Brute Force
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
    
    // Solution 3: Using Stack
    public static List<Integer> findLeadersUsingStack(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        stack.push(arr[arr.length - 1]);
        
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] > stack.peek()) {
                stack.push(arr[i]);
            }
        }
        
        List<Integer> leaders = new ArrayList<>();
        while (!stack.isEmpty()) {
            leaders.add(stack.pop());
        }
        
        return leaders;
    }
    
    // Solution 4: Using ArrayDeque
    public static List<Integer> findLeadersUsingDeque(int[] arr) {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.push(arr[arr.length - 1]);
        
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] > deque.peek()) {
                deque.push(arr[i]);
            }
        }
        
        return new ArrayList<>(deque);
    }
    
    // Solution 5: Recursive Approach
    public static List<Integer> findLeadersRecursive(int[] arr) {
        List<Integer> leaders = new ArrayList<>();
        findLeadersRecursiveHelper(arr, arr.length - 1, Integer.MIN_VALUE, leaders);
        Collections.reverse(leaders);
        return leaders;
    }
    
    private static void findLeadersRecursiveHelper(int[] arr, int index, 
                                                   int maxSoFar, List<Integer> leaders) {
        if (index < 0) return;
        
        if (arr[index] > maxSoFar) {
            leaders.add(arr[index]);
            maxSoFar = arr[index];
        }
        
        findLeadersRecursiveHelper(arr, index - 1, maxSoFar, leaders);
    }
    
    // Solution 6: Using Java 8 Streams (with indices)
    public static List<Integer> findLeadersStreams(int[] arr) {
        return IntStream.range(0, arr.length)
                .filter(i -> {
                    for (int j = i + 1; j < arr.length; j++) {
                        if (arr[i] < arr[j]) return false;
                    }
                    return true;
                })
                .mapToObj(i -> arr[i])
                .collect(Collectors.toList());
    }
    
    // Bonus: Find leaders with their positions
    public static Map<Integer, Integer> findLeadersWithPositions(int[] arr) {
        Map<Integer, Integer> leaderPositions = new LinkedHashMap<>();
        
        int maxFromRight = Integer.MIN_VALUE;
        
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] > maxFromRight) {
                maxFromRight = arr[i];
                leaderPositions.put(i, arr[i]);
            }
        }
        
        return leaderPositions;
    }
    
    public static void main(String[] args) {
        int[][] testArrays = {
            {16, 17, 4, 3, 5, 2},
            {1, 2, 3, 4, 5, 6},
            {6, 5, 4, 3, 2, 1},
            {7, 4, 5, 6, 3, 2, 1, 8},
            {10, 20, 30, 40, 50, 40, 30, 20, 10}
        };
        
        for (int i = 0; i < testArrays.length; i++) {
            int[] arr = testArrays[i];
            System.out.println("\n--- Test Array " + (i + 1) + ": " + Arrays.toString(arr) + " ---");
            
            System.out.println("1. Optimized (Right to Left): " + findLeadersOptimized(arr));
            System.out.println("2. Brute Force: " + findLeadersBruteForce(arr));
            System.out.println("3. Using Stack: " + findLeadersUsingStack(arr));
            System.out.println("4. Using Deque: " + findLeadersUsingDeque(arr));
            System.out.println("5. Recursive: " + findLeadersRecursive(arr));
            System.out.println("6. Using Streams: " + findLeadersStreams(arr));
            
            System.out.println("Bonus - Leaders with positions:");
            Map<Integer, Integer> leadersWithPos = findLeadersWithPositions(arr);
            leadersWithPos.forEach((pos, val) -> 
                System.out.println("   Position " + pos + ": " + val));
        }
    }
}