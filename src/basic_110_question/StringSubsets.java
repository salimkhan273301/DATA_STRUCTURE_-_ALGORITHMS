package basic_110_question;

import java.util.*;

public class StringSubsets {
    
    // Solution 1: Using Recursion
    public static List<String> findSubsetsRecursive(String str) {
        List<String> subsets = new ArrayList<>();
        generateSubsetsRecursive(str, 0, "", subsets);
        return subsets;
    }
    
    private static void generateSubsetsRecursive(String str, int index, String current, List<String> subsets) {
        if (index == str.length()) {
            if (!current.isEmpty()) {
                subsets.add(current);
            }
            return;
        }
        
        // Include current character
        generateSubsetsRecursive(str, index + 1, current + str.charAt(index), subsets);
        // Exclude current character
        generateSubsetsRecursive(str, index + 1, current, subsets);
    }
    
    // Solution 2: Using Bit Manipulation
    public static List<String> findSubsetsUsingBitManipulation(String str) {
        List<String> subsets = new ArrayList<>();
        int n = str.length();
        int totalSubsets = (int) Math.pow(2, n);
        
        for (int i = 1; i < totalSubsets; i++) {
            StringBuilder subset = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    subset.append(str.charAt(j));
                }
            }
            subsets.add(subset.toString());
        }
        
        return subsets;
    }
    
    // Solution 3: Using Iterative Approach
    public static List<String> findSubsetsIterative(String str) {
        List<String> subsets = new ArrayList<>();
        subsets.add(""); // Start with empty string
        
        for (int i = 0; i < str.length(); i++) {
            int size = subsets.size();
            for (int j = 0; j < size; j++) {
                subsets.add(subsets.get(j) + str.charAt(i));
            }
        }
        
        subsets.remove(""); // Remove empty string if needed
        return subsets;
    }
    
    // Solution 4: Using Queue
    public static List<String> findSubsetsUsingQueue(String str) {
        List<String> subsets = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        queue.add("");
        
        for (char c : str.toCharArray()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String current = queue.poll();
                String newSubset = current + c;
                queue.add(current);
                queue.add(newSubset);
                if (!newSubset.isEmpty()) {
                    subsets.add(newSubset);
                }
            }
        }
        
        return subsets;
    }
    
    // Solution 5: Using Backtracking
    public static List<String> findSubsetsBacktracking(String str) {
        List<String> subsets = new ArrayList<>();
        backtrack(subsets, new StringBuilder(), str, 0);
        return subsets;
    }
    
    private static void backtrack(List<String> subsets, StringBuilder current, String str, int start) {
        for (int i = start; i < str.length(); i++) {
            current.append(str.charAt(i));
            subsets.add(current.toString());
            backtrack(subsets, current, str, i + 1);
            current.deleteCharAt(current.length() - 1);
        }
    }
    
    // Solution 6: Using Power Set Formula with List
    public static List<String> findSubsetsPowerSet(String str) {
        List<String> subsets = new ArrayList<>();
        int n = str.length();
        
        for (int i = 0; i < (1 << n); i++) {
            StringBuilder subset = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) > 0) {
                    subset.append(str.charAt(j));
                }
            }
            if (subset.length() > 0) {
                subsets.add(subset.toString());
            }
        }
        
        return subsets;
    }
    
    public static void main(String[] args) {
        String test = "abc";
        System.out.println("Original String: " + test);
        
        System.out.println("\n1. Recursive Approach:");
        System.out.println(findSubsetsRecursive(test));
        
        System.out.println("\n2. Bit Manipulation:");
        System.out.println(findSubsetsUsingBitManipulation(test));
        
        System.out.println("\n3. Iterative Approach:");
        System.out.println(findSubsetsIterative(test));
        
        System.out.println("\n4. Using Queue:");
        System.out.println(findSubsetsUsingQueue(test));
        
        System.out.println("\n5. Using Backtracking:");
        System.out.println(findSubsetsBacktracking(test));
        
        System.out.println("\n6. Power Set Formula:");
        System.out.println(findSubsetsPowerSet(test));
    }
}
