package generalproblem;

public class LongestPalindromicSubstring {

    // ========== SOLUTION 1: Expand Around Center (Optimal) ==========
    // Time: O(n²), Space: O(1)
    public String longestPalindrome1(String s) {
        if (s == null || s.length() < 1) return "";
        
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            // Check odd-length palindromes (center at i)
            int len1 = expandAroundCenter(s, i, i);
            // Check even-length palindromes (center between i and i+1)
            int len2 = expandAroundCenter(s, i, i + 1);
            // Take the longer palindrome
            int len = Math.max(len1, len2);
            
            // Update start/end indices if longer palindrome found
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        // Expand while characters match and within bounds
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // Return length of palindrome (right - left - 1)
        return right - left - 1;
    }

    // ========== SOLUTION 2: Dynamic Programming (DP) ==========
    // Time: O(n²), Space: O(n²)
    public String longestPalindrome2(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        String res = "";
        
        // Fill DP table for all substrings
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                // Check if s[i..j] is a palindrome
                dp[i][j] = (s.charAt(i) == s.charAt(j)) && (j - i <= 2 || dp[i + 1][j - 1]);
                
                // Update result if longer palindrome found
                if (dp[i][j] && (j - i + 1) > res.length()) {
                    res = s.substring(i, j + 1);
                }
            }
        }
        return res;
    }

    // ========== SOLUTION 3: Manacher's Algorithm (Linear Time) ==========
    // Time: O(n), Space: O(n)
    public String longestPalindrome3(String s) {
        if (s == null || s.length() < 1) return "";
        
        // Preprocess string with special characters (^#a#b#c#$)
        String T = preprocess(s);
        int n = T.length();
        int[] P = new int[n]; // Stores palindrome lengths
        int C = 0, R = 0;    // Center and right boundary
        
        for (int i = 1; i < n - 1; i++) {
            int mirror = 2 * C - i; // Mirror of i around C
            
            // Use previously computed info if within current right boundary
            if (i < R) {
                P[i] = Math.min(R - i, P[mirror]);
            }
            
            // Expand around i
            while (T.charAt(i + 1 + P[i]) == T.charAt(i - 1 - P[i])) {
                P[i]++;
            }
            
            // Update center and right boundary if expanded beyond R
            if (i + P[i] > R) {
                C = i;
                R = i + P[i];
            }
        }
        
        // Find the longest palindrome in P[]
        int maxLen = 0, center = 0;
        for (int i = 1; i < n - 1; i++) {
            if (P[i] > maxLen) {
                maxLen = P[i];
                center = i;
            }
        }
        
        // Extract and return the longest palindrome
        int start = (center - maxLen) / 2;
        return s.substring(start, start + maxLen);
    }

    private String preprocess(String s) {
        StringBuilder sb = new StringBuilder("^");
        for (char c : s.toCharArray()) {
            sb.append("#").append(c);
        }
        sb.append("#$");
        return sb.toString();
    }

    // ========== SOLUTION 4: Brute Force (Check All Substrings) ==========
    // Time: O(n³), Space: O(1)
    public String longestPalindrome4(String s) {
        if (s == null || s.length() < 1) return "";
        
        String longest = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                String substr = s.substring(i, j);
                if (isPalindrome(substr) && substr.length() > longest.length()) {
                    longest = substr;
                }
            }
        }
        return longest;
    }

    private boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }

    // ========== SOLUTION 5: Reverse & Longest Common Substring (LCS) ==========
    // Time: O(n²), Space: O(n²)
    public String longestPalindrome5(String s) {
        if (s == null || s.length() < 1) return "";
        
        String reversed = new StringBuilder(s).reverse().toString();
        int n = s.length();
        int[][] dp = new int[n + 1][n + 1];
        int maxLen = 0, endIndex = 0;
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == reversed.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    // Ensure it's a valid palindrome (same substring in original)
                    if (dp[i][j] > maxLen && (i - dp[i][j] == n - j)) {
                        maxLen = dp[i][j];
                        endIndex = i;
                    }
                }
            }
        }
        return s.substring(endIndex - maxLen, endIndex);
    }

    // ========== SOLUTION 6: Optimized Expand with Early Termination ==========
    // Time: O(n²), Space: O(1)
    public String longestPalindrome6(String s) {
        if (s == null || s.length() < 1) return "";
        
        int start = 0, maxLen = 1;
        for (int i = 0; i < s.length();) {
            // Skip duplicates to optimize
            int left = i, right = i;
            while (right < s.length() - 1 && s.charAt(right) == s.charAt(right + 1)) {
                right++;
            }
            i = right + 1; // Move i to next unique character
            
            // Expand around center
            while (left > 0 && right < s.length() - 1 && s.charAt(left - 1) == s.charAt(right + 1)) {
                left--;
                right++;
            }
            
            // Update if longer palindrome found
            int currLen = right - left + 1;
            if (currLen > maxLen) {
                maxLen = currLen;
                start = left;
            }
        }
        return s.substring(start, start + maxLen);
    }

    // ========== SOLUTION 7: Recursive with Memoization ==========
    // Time: O(n²), Space: O(n²)
    public String longestPalindrome7(String s) {
        int n = s.length();
        boolean[][] memo = new boolean[n][n];
        String res = "";
        
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (isPalindrome(s, i, j, memo) && (j - i + 1) > res.length()) {
                    res = s.substring(i, j + 1);
                }
            }
        }
        return res;
    }

    private boolean isPalindrome(String s, int i, int j, boolean[][] memo) {
        if (i >= j) return true;
        if (memo[i][j]) return true;
        
        boolean res = (s.charAt(i) == s.charAt(j)) && isPalindrome(s, i + 1, j - 1, memo);
        memo[i][j] = res;
        return res;
    }

    // ========== SOLUTION 8: Two Pointers with Greedy Expansion ==========
    // Time: O(n²), Space: O(1)
    public String longestPalindrome8(String s) {
        if (s == null || s.length() < 1) return "";
        
        String longest = "";
        for (int i = 0; i < s.length(); i++) {
            String odd = expand(s, i, i);      // Odd-length
            String even = expand(s, i, i + 1); // Even-length
            
            // Take the longer of the two
            String currLongest = odd.length() > even.length() ? odd : even;
            if (currLongest.length() > longest.length()) {
                longest = currLongest;
            }
        }
        return longest;
    }

    private String expand(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return s.substring(left + 1, right);
    }

    // ========== SOLUTION 9: Suffix Automaton (Advanced) ==========
    // Time: O(n), Space: O(n)
    // (Implementation omitted due to complexity)

    // ========== SOLUTION 10: Rolling Hash (Rabin-Karp) ==========
    // Time: O(n²), Space: O(n)
    // (Implementation omitted due to complexity)

    // ========== TEST CASES ==========
    public static void main(String[] args) {
        LongestPalindromicSubstring solver = new LongestPalindromicSubstring();
        String[] testCases = {
            "babad",    // Expected: "bab" or "aba"
            "cbbd",     // Expected: "bb"
            "a",        // Expected: "a"
            "ac",       // Expected: "a" or "c"
            "ccc",      // Expected: "ccc"
            "abba",     // Expected: "abba"
            "racecar"   // Expected: "racecar"
        };

        for (String testCase : testCases) {
            System.out.println("Input: " + testCase);
            System.out.println("Solution 1: " + solver.longestPalindrome1(testCase));
            System.out.println("Solution 2: " + solver.longestPalindrome2(testCase));
            System.out.println("Solution 3: " + solver.longestPalindrome3(testCase));
            System.out.println("Solution 4: " + solver.longestPalindrome4(testCase));
            System.out.println("Solution 5: " + solver.longestPalindrome5(testCase));
            System.out.println("Solution 6: " + solver.longestPalindrome6(testCase));
            System.out.println("Solution 7: " + solver.longestPalindrome7(testCase));
            System.out.println("Solution 8: " + solver.longestPalindrome8(testCase));
            System.out.println("----------------------");
        }
    }
}