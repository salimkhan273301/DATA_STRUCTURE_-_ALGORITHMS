package sliding_window;

import java.util.*;

public class NoOfAnagramsExist {

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();

        if (s.length() < p.length()) return result;

        int[] pFreq = new int[26];
        int[] sFreq = new int[26];

        // Build frequency of p
        for (char c : p.toCharArray()) {
            pFreq[c - 'a']++;
        }

        int windowSize = p.length();

        // Fill first window in s
        for (int i = 0; i < windowSize; i++) {
            sFreq[s.charAt(i) - 'a']++;
        }

        // Check first window
        if (Arrays.equals(pFreq, sFreq)) {
            result.add(0);
        }

        // Slide window
        for (int j = windowSize; j < s.length(); j++) {
            // Remove char going out of window
            sFreq[s.charAt(j - windowSize) - 'a']--;

            // Add new char coming into window
            sFreq[s.charAt(j) - 'a']++;

            if (Arrays.equals(pFreq, sFreq)) {
                result.add(j - windowSize + 1);
            }
        }

        return result;
    }
    
    public List<Integer> method2_hashmap(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s.length() < p.length()) return result;

        Map<Character, Integer> pMap = new HashMap<>();
        Map<Character, Integer> windowMap = new HashMap<>();

        for (char c : p.toCharArray()) pMap.put(c, pMap.getOrDefault(c, 0) + 1);

        int window = p.length();

        for (int i = 0; i < window; i++) {
            char c = s.charAt(i);
            windowMap.put(c, windowMap.getOrDefault(c, 0) + 1);
        }

        if (pMap.equals(windowMap)) result.add(0);

        for (int i = window; i < s.length(); i++) {
            char out = s.charAt(i - window);
            char in = s.charAt(i);

            windowMap.put(out, windowMap.get(out) - 1);
            if (windowMap.get(out) == 0) windowMap.remove(out);

            windowMap.put(in, windowMap.getOrDefault(in, 0) + 1);

            if (pMap.equals(windowMap)) result.add(i - window + 1);
        }

        return result;
    }
    
    
    
    public List<Integer> method3_sorting(String s, String p) {
        List<Integer> result = new ArrayList<>();
        int pLen = p.length();
        if (s.length() < pLen) return result;

        char[] sortedP = p.toCharArray();
        Arrays.sort(sortedP);

        for (int i = 0; i <= s.length() - pLen; i++) {
            String window = s.substring(i, i + pLen);
            char[] sortedWindow = window.toCharArray();
            Arrays.sort(sortedWindow);
            if (Arrays.equals(sortedWindow, sortedP)) result.add(i);
        }

        return result;
    }



    // Test cases
    public static void main(String[] args) {
    	NoOfAnagramsExist sol = new NoOfAnagramsExist();

        // Test 1
        String s1 = "cbaebabacd", p1 = "abc";
        System.out.println("Test 1: " + sol.findAnagrams(s1, p1)); // Expected: [0, 6]

        // Test 2
        String s2 = "abab", p2 = "ab";
        System.out.println("Test 2: " + sol.findAnagrams(s2, p2)); // Expected: [0,1,2]

        // Test 3
        String s3 = "af", p3 = "be";
        System.out.println("Test 3: " + sol.findAnagrams(s3, p3)); // Expected: []

        // Test 4
        String s4 = "aaaaaaaaaa", p4 = "aaaaaaaaaaaaa";
        System.out.println("Test 4: " + sol.findAnagrams(s4, p4)); // Expected: []

        // Test 5
        String s5 = "abc", p5 = "abc";
        System.out.println("Test 5: " + sol.findAnagrams(s5, p5)); // Expected: [0]
    }
}
