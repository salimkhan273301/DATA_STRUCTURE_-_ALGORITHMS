package algoBasedSolution;

import java.util.ArrayList;
import java.util.List;

/**
 * PatternMatcher - A comprehensive class for finding all occurrences of a pattern in text
 * using various algorithms including KMP, Rabin-Karp, Naive, and Built-in methods.
 */
public class PatternMatcher {

    /**
     * KMP Algorithm - Most efficient pattern matching algorithm
     * Time Complexity: O(n + m)
     * Space Complexity: O(m)
     */
    public static List<Integer> kmpSearch(String txt, String pat) {
        List<Integer> result = new ArrayList<>();
        if (pat == null || pat.isEmpty() || txt == null || txt.isEmpty() || pat.length() > txt.length()) {
            return result;
        }
        
        int n = txt.length();
        int m = pat.length();
        
        // Precompute LPS (Longest Prefix Suffix) array
        int[] lps = computeLPS(pat);
        
        int i = 0; // index for text
        int j = 0; // index for pattern
        
        while (i < n) {
            if (txt.charAt(i) == pat.charAt(j)) {
                i++;
                j++;
                
                if (j == m) {
                    result.add(i - j); // Found a match
                    j = lps[j - 1]; // Continue searching
                }
            } else {
                if (j != 0) {
                    j = lps[j - 1]; // Use LPS to skip unnecessary comparisons
                } else {
                    i++; // Move to next character in text
                }
            }
        }
        
        return result;
    }
    
    private static int[] computeLPS(String pat) {
        int m = pat.length();
        int[] lps = new int[m];
        int length = 0; // length of previous longest prefix suffix
        int i = 1;
        
        while (i < m) {
            if (pat.charAt(i) == pat.charAt(length)) {
                length++;
                lps[i] = length;
                i++;
            } else {
                if (length != 0) {
                    length = lps[length - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }

    /**
     * Rabin-Karp Algorithm - Uses rolling hash for pattern matching
     * Time Complexity: O(n + m) average, O(nm) worst case
     */
    public static List<Integer> rabinKarpSearch(String txt, String pat) {
        List<Integer> result = new ArrayList<>();
        if (pat == null || pat.isEmpty() || txt == null || txt.isEmpty() || pat.length() > txt.length()) {
            return result;
        }
        
        int n = txt.length();
        int m = pat.length();
        int prime = 101; // Prime number for hashing
        int d = 256; // Number of characters in alphabet
        
        // Calculate h = d^(m-1) % prime
        int h = 1;
        for (int i = 0; i < m - 1; i++) {
            h = (h * d) % prime;
        }
        
        // Calculate initial hash values
        int patHash = 0;
        int txtHash = 0;
        for (int i = 0; i < m; i++) {
            patHash = (d * patHash + pat.charAt(i)) % prime;
            txtHash = (d * txtHash + txt.charAt(i)) % prime;
        }
        
        // Slide pattern over text
        for (int i = 0; i <= n - m; i++) {
            // Check hash values first (spurious hit check)
            if (patHash == txtHash) {
                // Verify actual string match
                boolean match = true;
                for (int j = 0; j < m; j++) {
                    if (txt.charAt(i + j) != pat.charAt(j)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    result.add(i);
                }
            }
            
            // Calculate rolling hash for next window
            if (i < n - m) {
                txtHash = (d * (txtHash - txt.charAt(i) * h) + txt.charAt(i + m)) % prime;
                // Ensure positive hash value
                if (txtHash < 0) {
                    txtHash += prime;
                }
            }
        }
        
        return result;
    }

    /**
     * Naive Pattern Matching Algorithm - Simple but inefficient
     * Time Complexity: O(n * m)
     */
    public static List<Integer> naiveSearch(String txt, String pat) {
        List<Integer> result = new ArrayList<>();
        if (pat == null || pat.isEmpty() || txt == null || txt.isEmpty() || pat.length() > txt.length()) {
            return result;
        }
        
        int n = txt.length();
        int m = pat.length();
        
        for (int i = 0; i <= n - m; i++) {
            int j;
            for (j = 0; j < m; j++) {
                if (txt.charAt(i + j) != pat.charAt(j)) {
                    break;
                }
            }
            if (j == m) {
                result.add(i);
            }
        }
        
        return result;
    }

    /**
     * Built-in String Method - Uses Java's internal implementation
     * Time Complexity: O(n + m)
     */
    public static List<Integer> builtinSearch(String txt, String pat) {
        List<Integer> result = new ArrayList<>();
        if (pat == null || pat.isEmpty() || txt == null || txt.isEmpty() || pat.length() > txt.length()) {
            return result;
        }
        
        int start = 0;
        while (true) {
            int index = txt.indexOf(pat, start);
            if (index == -1) {
                break;
            }
            result.add(index);
            start = index + 1; // Continue searching from next position
        }
        
        return result;
    }

    /**
     * Main method with test cases
     */
    public static void main(String[] args) {
        // Test cases from the problem
        String[][] testCases = {
            {"abcab", "ab"},      // Expected: [0, 3]
            {"abesdu", "edu"},    // Expected: []
            {"aaaaaa", "aa"},     // Expected: [0, 1, 2, 3, 4]
            {"mississippi", "issi"}, // Expected: [1, 4]
            {"hello world", "world"}, // Expected: [6]
            {"", "abc"},          // Edge case: empty text
            {"abc", ""},          // Edge case: empty pattern
            {"a", "a"},           // Edge case: single character
            {"abc", "def"}        // Edge case: no match
        };
        
        System.out.println("Pattern Matcher - All Algorithm Results");
        System.out.println("=" .repeat(50));
        
        for (int i = 0; i < testCases.length; i++) {
            String txt = testCases[i][0];
            String pat = testCases[i][1];
            
            System.out.println("\nTest Case " + (i + 1) + ":");
            System.out.println("Text: \"" + txt + "\"");
            System.out.println("Pattern: \"" + pat + "\"");
            
            List<Integer> kmpResult = kmpSearch(txt, pat);
            List<Integer> rkResult = rabinKarpSearch(txt, pat);
            List<Integer> naiveResult = naiveSearch(txt, pat);
            List<Integer> builtinResult = builtinSearch(txt, pat);
            
            System.out.println("KMP Algorithm: " + kmpResult);
            System.out.println("Rabin-Karp:    " + rkResult);
            System.out.println("Naive:         " + naiveResult);
            System.out.println("Built-in:      " + builtinResult);
            
            // Verify all algorithms give same result
            boolean allMatch = kmpResult.equals(rkResult) && 
                              rkResult.equals(naiveResult) && 
                              naiveResult.equals(builtinResult);
            System.out.println("All algorithms match: " + allMatch);
        }
        
        // Performance comparison for large input
        System.out.println("\n" + "=" .repeat(50));
        System.out.println("Performance Comparison:");
        
        String largeText = "a".repeat(10000) + "b" + "a".repeat(10000);
        String pattern = "a".repeat(100) + "b";
        
        long startTime = System.currentTimeMillis();
        List<Integer> kmpResult = kmpSearch(largeText, pattern);
        long kmpTime = System.currentTimeMillis() - startTime;
        
        startTime = System.currentTimeMillis();
        List<Integer> rkResult = rabinKarpSearch(largeText, pattern);
        long rkTime = System.currentTimeMillis() - startTime;
        
        startTime = System.currentTimeMillis();
        List<Integer> naiveResult = naiveSearch(largeText, pattern);
        long naiveTime = System.currentTimeMillis() - startTime;
        
        System.out.println("Large input (KMP): " + kmpTime + "ms");
        System.out.println("Large input (Rabin-Karp): " + rkTime + "ms");
        System.out.println("Large input (Naive): " + naiveTime + "ms");
        System.out.println("Results match: " + 
            (kmpResult.equals(rkResult) && rkResult.equals(naiveResult)));
    }
}