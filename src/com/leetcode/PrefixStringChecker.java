package com.leetcode;

public class PrefixStringChecker {

    // Solution 1: Using StringBuilder (Naive Approach)
    public boolean isPrefixString1(String s, String[] words) {
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.append(word);
            if (sb.toString().equals(s)) {
                return true;
            }
            if (sb.length() > s.length()) {
                return false;
            }
        }
        return false;
    }

    // Solution 2: Using String Concatenation (Alternative to StringBuilder)
    public boolean isPrefixString2(String s, String[] words) {
        String concat = "";
        for (String word : words) {
            concat += word;
            if (concat.equals(s)) {
                return true;
            }
            if (concat.length() > s.length()) {
                return false;
            }
        }
        return false;
    }

    // Solution 3: Character-by-Character Comparison (Optimal for Space)
    public boolean isPrefixString3(String s, String[] words) {
        int index = 0;
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                if (index >= s.length() || word.charAt(i) != s.charAt(index)) {
                    return false;
                }
                index++;
            }
            if (index == s.length()) {
                return true;
            }
        }
        return false;
    }

    // Solution 4: Using Substring Matching (Alternative Approach)
    public boolean isPrefixString4(String s, String[] words) {
        int start = 0;
        for (String word : words) {
            int end = start + word.length();
            if (end > s.length() || !s.substring(start, end).equals(word)) {
                return false;
            }
            start = end;
            if (start == s.length()) {
                return true;
            }
        }
        return false;
    }

    // Solution 5: Early Termination with Index Tracking
    public boolean isPrefixString5(String s, String[] words) {
        int sIndex = 0;
        for (String word : words) {
            if (sIndex + word.length() > s.length()) {
                return false;
            }
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) != s.charAt(sIndex++)) {
                    return false;
                }
            }
            if (sIndex == s.length()) {
                return true;
            }
        }
        return false;
    }

    // Test Cases
    public static void main(String[] args) {
        PrefixStringChecker checker = new PrefixStringChecker();

        // Test Case 1: Basic case (should return true)
        String s1 = "iloveleetcode";
        String[] words1 = {"i", "love", "leetcode", "apples"};
        System.out.println("Test Case 1 (Expected: true)");
        System.out.println("Solution 1: " + checker.isPrefixString1(s1, words1));
        System.out.println("Solution 2: " + checker.isPrefixString2(s1, words1));
        System.out.println("Solution 3: " + checker.isPrefixString3(s1, words1));
        System.out.println("Solution 4: " + checker.isPrefixString4(s1, words1));
        System.out.println("Solution 5: " + checker.isPrefixString5(s1, words1));
        System.out.println();

        // Test Case 2: Partial match (should return false)
        String s2 = "iloveleetcode";
        String[] words2 = {"i", "love", "code"};
        System.out.println("Test Case 2 (Expected: false)");
        System.out.println("Solution 1: " + checker.isPrefixString1(s2, words2));
        System.out.println("Solution 2: " + checker.isPrefixString2(s2, words2));
        System.out.println("Solution 3: " + checker.isPrefixString3(s2, words2));
        System.out.println("Solution 4: " + checker.isPrefixString4(s2, words2));
        System.out.println("Solution 5: " + checker.isPrefixString5(s2, words2));
        System.out.println();

        // Test Case 3: Empty string (should return true if words are empty)
        String s3 = "";
        String[] words3 = {};
        System.out.println("Test Case 3 (Expected: true)");
        System.out.println("Solution 1: " + checker.isPrefixString1(s3, words3));
        System.out.println("Solution 2: " + checker.isPrefixString2(s3, words3));
        System.out.println("Solution 3: " + checker.isPrefixString3(s3, words3));
        System.out.println("Solution 4: " + checker.isPrefixString4(s3, words3));
        System.out.println("Solution 5: " + checker.isPrefixString5(s3, words3));
        System.out.println();

        // Test Case 4: Words longer than s (should return false)
        String s4 = "hello";
        String[] words4 = {"hello", "world"};
        System.out.println("Test Case 4 (Expected: true)");
        System.out.println("Solution 1: " + checker.isPrefixString1(s4, words4));
        System.out.println("Solution 2: " + checker.isPrefixString2(s4, words4));
        System.out.println("Solution 3: " + checker.isPrefixString3(s4, words4));
        System.out.println("Solution 4: " + checker.isPrefixString4(s4, words4));
        System.out.println("Solution 5: " + checker.isPrefixString5(s4, words4));
        System.out.println();

        // Test Case 5: No match (should return false)
        String s5 = "abc";
        String[] words5 = {"a", "b", "d"};
        System.out.println("Test Case 5 (Expected: false)");
        System.out.println("Solution 1: " + checker.isPrefixString1(s5, words5));
        System.out.println("Solution 2: " + checker.isPrefixString2(s5, words5));
        System.out.println("Solution 3: " + checker.isPrefixString3(s5, words5));
        System.out.println("Solution 4: " + checker.isPrefixString4(s5, words5));
        System.out.println("Solution 5: " + checker.isPrefixString5(s5, words5));
    }
}
