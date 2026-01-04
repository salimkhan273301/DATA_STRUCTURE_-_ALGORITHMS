package com.leetcode;

import java.util.LinkedList;
import java.util.Queue;

import java.util.*;
import java.util.stream.Collectors;

public class SubsequenceChecker {

    // ================== METHOD IMPLEMENTATIONS ==================

    // 1. Two-pointer approach (optimal for single query)
    public static boolean isSubsequenceTwoPointer(String s, String t) {
        int p1 = 0, p2 = 0;
        while (p1 < s.length() && p2 < t.length()) {
            if (s.charAt(p1) == t.charAt(p2)) {
                p1++;
            }
            p2++;
        }
        return p1 == s.length();
    }

    // 2. Recursive approach (not efficient, just for demonstration)
    public static boolean isSubsequenceRecursive(String s, String t) {
        return helper(s, t, 0, 0);
    }

    private static boolean helper(String s, String t, int sIndex, int tIndex) {
        if (sIndex == s.length()) return true;
        if (tIndex == t.length()) return false;
        if (s.charAt(sIndex) == t.charAt(tIndex)) {
            return helper(s, t, sIndex + 1, tIndex + 1);
        }
        return helper(s, t, sIndex, tIndex + 1);
    }

    // 3. Using indexOf (clean and efficient)
    public static boolean isSubsequenceIndexOf(String s, String t) {
        int index = -1;
        for (char c : s.toCharArray()) {
            index = t.indexOf(c, index + 1);
            if (index == -1) return false;
        }
        return true;
    }

    // 4. Using Queue (conceptually clear)
    public static boolean isSubsequenceQueue(String s, String t) {
        Queue<Character> queue = s.chars()
                                .mapToObj(c -> (char) c)
                                .collect(Collectors.toCollection(LinkedList::new));
        for (char c : t.toCharArray()) {
            if (!queue.isEmpty() && c == queue.peek()) {
                queue.poll();
            }
        }
        return queue.isEmpty();
    }

    // 5. Binary Search (optimal for multiple queries on same t)
    public static boolean isSubsequenceBinarySearch(String s, String t) {
        Map<Character, List<Integer>> charIndices = new HashMap<>();
        // Preprocess t: store indices of each character
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            charIndices.putIfAbsent(c, new ArrayList<>());
            charIndices.get(c).add(i);
        }

        int prevIndex = -1;
        for (char c : s.toCharArray()) {
            if (!charIndices.containsKey(c)) return false;
            List<Integer> indices = charIndices.get(c);
            // Find the first index > prevIndex using binary search
            int insertionPoint = Collections.binarySearch(indices, prevIndex + 1);
            if (insertionPoint < 0) {
                insertionPoint = -insertionPoint - 1;
            }
            if (insertionPoint == indices.size()) return false;
            prevIndex = indices.get(insertionPoint);
        }
        return true;
    }

    // ================== TEST CASES ==================

    public static void main(String[] args) {
        // Test case format: {s, t, expected}
        String[][] testCases = {
            {"abc", "ahbgdc"},      // Normal case
            {"axc", "ahbgdc"},     // Non-subsequence
            {"", "ahbgdc"},         // Empty s is always true
            {"abc", ""},           // Empty t but non-empty s
            {"bdc", "bdc"},         // Exact match
            {"aaa", "aaab",},        // Multiple same chars
            {"ace", "abcde", },       // LeetCode example
            {"aec", "abcde",}       // LeetCode example (false)
        };

        // Run all methods on each test case
        for (String[] testCase : testCases) {
            String s = testCase[0];
            String t = testCase[1];
            boolean expected = testCase[2].equals("true");

            System.out.printf("\nTest case: s=\"%s\", t=\"%s\" (Expected: %b)\n", s, t, expected);
            System.out.println("Two-pointer:    " + (isSubsequenceTwoPointer(s, t) == expected ? "✓" : "✗"));
            System.out.println("Recursive:      " + (isSubsequenceRecursive(s, t) == expected ? "✓" : "✗"));
            System.out.println("indexOf:        " + (isSubsequenceIndexOf(s, t) == expected ? "✓" : "✗"));
            System.out.println("Queue:          " + (isSubsequenceQueue(s, t) == expected ? "✓" : "✗"));
            System.out.println("Binary Search:  " + (isSubsequenceBinarySearch(s, t) == expected ? "✓" : "✗"));
        }
    }
}