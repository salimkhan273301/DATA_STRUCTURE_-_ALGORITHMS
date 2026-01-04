package string;

import java.util.*;

public class MinCharsToMakePalindrome {

    // ----------------- 1. Brute Force -----------------
    // Keep checking longest palindrome prefix by removing characters from end.
    // TC: O(n^2), SC: O(1)
    public int bruteForce(String s) {
        int n = s.length();
        for (int i = n; i > 0; i--) {
            if (isPalindrome(s, 0, i - 1)) {
                return n - i;
            }
        }
        return n - 1;
    }
    private boolean isPalindrome(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) return false;
        }
        return true;
    }

    // ----------------- 2. Reverse + Longest Prefix Suffix (KMP) -----------------
    // Create s + "$" + reverse(s), then find longest prefix which is also suffix.
    // TC: O(n), SC: O(n)
    public int kmpApproach(String s) {
        String rev = new StringBuilder(s).reverse().toString();
        String concat = s + "$" + rev;
        int lps = computeLPS(concat);
        return s.length() - lps;
    }
    private int computeLPS(String str) {
        int n = str.length();
        int[] lps = new int[n];
        int len = 0;
        for (int i = 1; i < n;) {
            if (str.charAt(i) == str.charAt(len)) {
                lps[i++] = ++len;
            } else if (len > 0) {
                len = lps[len - 1];
            } else {
                lps[i++] = 0;
            }
        }
        return lps[n - 1];
    }

    // ----------------- 3. Rolling Hash (Rabin-Karp style) -----------------
    // Build rolling hash for prefix/suffix and find max palindrome prefix.
    // TC: O(n), SC: O(1)
    public int rollingHashApproach(String s) {
        long mod = 1000000007L, base = 131;
        long forward = 0, backward = 0, pow = 1;
        int best = 0, n = s.length();

        for (int i = 0; i < n; i++) {
            int val = s.charAt(i) - 'a' + 1;
            forward = (forward * base + val) % mod;
            backward = (backward + val * pow) % mod;
            pow = (pow * base) % mod;
            if (forward == backward) best = i + 1;
        }
        return n - best;
    }

    // ----------------- 4. Recursion -----------------
    // Recurse until you find a palindrome prefix.
    // TC: O(n^2), SC: O(n) (recursion stack)
    public int recursiveApproach(String s) {
        return helper(s, s.length());
    }
    private int helper(String s, int n) {
        if (n == 0) return s.length() - 1;
        if (isPalindrome(s, 0, n - 1)) return s.length() - n;
        return helper(s, n - 1);
    }

    // ----------------- 5. Two Pointer with Reverse Trick -----------------
    // Compare original and reversed until max match found.
    // TC: O(n^2) worst, SC: O(n)
    public int twoPointerReverse(String s) {
        String rev = new StringBuilder(s).reverse().toString();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (s.startsWith(rev.substring(i))) {
                return i;
            }
        }
        return n - 1;
    }

    // ----------------- Test Cases -----------------
    public static void main(String[] args) {
        MinCharsToMakePalindrome sol = new MinCharsToMakePalindrome();

        String[] tests = {"ABC", "AACECAAAA", "ABCD", "AAA", "ABBA"};
        for (String t : tests) {
            System.out.println("Input: " + t);
            System.out.println("BruteForce -> " + sol.bruteForce(t));
            System.out.println("KMP -> " + sol.kmpApproach(t));
            System.out.println("RollingHash -> " + sol.rollingHashApproach(t));
            System.out.println("Recursive -> " + sol.recursiveApproach(t));
            System.out.println("TwoPointerReverse -> " + sol.twoPointerReverse(t));
            System.out.println("--------------------------------------------------");
        }
    }
}
