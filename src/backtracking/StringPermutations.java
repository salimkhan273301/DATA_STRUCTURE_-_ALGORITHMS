package backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//import java.util.*;

public class StringPermutations {
    
    public static void main(String[] args) {
        String input = "ABC";
        System.out.println("Input: " + input);
        
        // Test all methods
        for (int i = 1; i <= 5; i++) {
            System.out.println("\nMethod " + i + ":");
            List<String> result = null;
            long startTime = System.nanoTime();
            
            switch (i) {
                case 1: result = permute1(input); break;
                case 2: result = permute2(input); break;
                case 3: result = permute3(input); break;
                case 4: result = permute4(input); break;
                case 5: result = permute5(input); break;
            }
            
            long endTime = System.nanoTime();
            System.out.println("Time: " + (endTime - startTime) + " ns");
            System.out.println("Count: " + result.size());
            System.out.println("Permutations: " + result);
        }
    }

    // Method 1: Backtracking with swapping (Optimal)
    // Time: O(n * n!), Space: O(n!)
    public static List<String> permute1(String s) {
        List<String> res = new ArrayList<>();
        char[] arr = s.toCharArray();
        backtrackSwap(arr, 0, res);
        return res;
    }
    
    private static void backtrackSwap(char[] arr, int idx, List<String> res) {
        if (idx == arr.length) {
            res.add(new String(arr));
            return;
        }
        for (int i = idx; i < arr.length; i++) {
            swap(arr, idx, i);
            backtrackSwap(arr, idx + 1, res);
            swap(arr, idx, i);
        }
    }

    // Method 2: Backtracking with boolean array
    // Time: O(n * n!), Space: O(n!)
    public static List<String> permute2(String s) {
        List<String> res = new ArrayList<>();
        backtrack(s.toCharArray(), new StringBuilder(), new boolean[s.length()], res);
        return res;
    }
    
    private static void backtrack(char[] arr, StringBuilder current, boolean[] used, List<String> res) {
        if (current.length() == arr.length) {
            res.add(current.toString());
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if (!used[i]) {
                used[i] = true;
                current.append(arr[i]);
                backtrack(arr, current, used, res);
                current.deleteCharAt(current.length() - 1);
                used[i] = false;
            }
        }
    }

    // Method 3: Iterative BFS approach
    // Time: O(n * n!), Space: O(n!)
    public static List<String> permute3(String s) {
        Queue<String> queue = new LinkedList<>();
        queue.offer("");
        
        for (char c : s.toCharArray()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String current = queue.poll();
                for (int j = 0; j <= current.length(); j++) {
                    String newPerm = current.substring(0, j) + c + current.substring(j);
                    queue.offer(newPerm);
                }
            }
        }
        return new ArrayList<>(queue);
    }

    // Method 4: Heap's Algorithm
    // Time: O(n * n!), Space: O(n!)
    public static List<String> permute4(String s) {
        List<String> res = new ArrayList<>();
        char[] arr = s.toCharArray();
        heapsAlgorithm(arr, arr.length, res);
        return res;
    }
    
    private static void heapsAlgorithm(char[] arr, int size, List<String> res) {
        if (size == 1) {
            res.add(new String(arr));
            return;
        }
        for (int i = 0; i < size; i++) {
            heapsAlgorithm(arr, size - 1, res);
            if (size % 2 == 1) {
                swap(arr, 0, size - 1);
            } else {
                swap(arr, i, size - 1);
            }
        }
    }

    // Method 5: Using recursive insertion
    // Time: O(n * n!), Space: O(n!)
    public static List<String> permute5(String s) {
        if (s.length() == 0) {
            List<String> res = new ArrayList<>();
            res.add("");
            return res;
        }
        
        List<String> result = new ArrayList<>();
        char first = s.charAt(0);
        String rest = s.substring(1);
        
        for (String perm : permute5(rest)) {
            for (int i = 0; i <= perm.length(); i++) {
                String newPerm = perm.substring(0, i) + first + perm.substring(i);
                result.add(newPerm);
            }
        }
        return result;
    }

    // Utility method for swapping characters
    private static void swap(char[] a, int i, int j) {
        char temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
