package sliding_window;

import java.util.*;

public class MaximumLengthSubstringWithTwoOccurrences {

    // ✅ Method 1: Sliding Window + Frequency Array
    public int slidingWindowArray(String s) {
        int[] cnt = new int[26];
        int left = 0, max = 0;
        for (int right = 0; right < s.length(); right++) {
            cnt[s.charAt(right) - 'a']++;
            while (cnt[s.charAt(right) - 'a'] > 2) {
                cnt[s.charAt(left++) - 'a']--;
            }
            max = Math.max(max, right - left + 1);
        }
        return max;
    }

    // ✅ Method 2: Sliding Window + HashMap
    public int slidingWindowMap(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int left = 0, max = 0;
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            map.put(c, map.getOrDefault(c, 0) + 1);
            while (map.get(c) > 2) {
                char out = s.charAt(left++);
                map.put(out, map.get(out) - 1);
            }
            max = Math.max(max, right - left + 1);
        }
        return max;
    }

    // ✅ Method 3: Brute Force (for small strings)
    public int bruteForce(String s) {
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            int[] cnt = new int[26];
            for (int j = i; j < s.length(); j++) {
                int index = s.charAt(j) - 'a';
                cnt[index]++;
                if (cnt[index] > 2) break;
                max = Math.max(max, j - i + 1);
            }
        }
        return max;
    }

    // ✅ Method 4: Sliding Window + Queue
    public int slidingWindowQueue(String s) {
        int[] count = new int[26];
        Queue<Character> window = new LinkedList<>();
        int max = 0;

        for (char c : s.toCharArray()) {
            window.add(c);
            count[c - 'a']++;
            while (count[c - 'a'] > 2) {
                char removed = window.poll();
                count[removed - 'a']--;
            }
            max = Math.max(max, window.size());
        }

        return max;
    }

    // ✅ Method 5: Sliding Window with extended ASCII (safe for all char sets)
    public int slidingWindowAscii(String s) {
        int[] freq = new int[128];
        int left = 0, max = 0;

        for (int right = 0; right < s.length(); right++) {
            freq[s.charAt(right)]++;
            while (freq[s.charAt(right)] > 2) {
                freq[s.charAt(left++)]--;
            }
            max = Math.max(max, right - left + 1);
        }

        return max;
    }

    // ✅ Test Runner
    public static void main(String[] args) {
        MaximumLengthSubstringWithTwoOccurrences sol = new MaximumLengthSubstringWithTwoOccurrences();

        String[] tests = {
            "bcbbbcba",    // Expected: 4
            "aaaa",        // Expected: 2
            "abc",         // Expected: 3
            "aabbaaacc",   // Expected: 6
            "",            // Expected: 0
            "abababab"     // Expected: 6
        };

        for (String s : tests) {
            System.out.printf("Input: \"%s\"\n", s);
            System.out.println("slidingWindowArray:  " + sol.slidingWindowArray(s));
            System.out.println("slidingWindowMap:    " + sol.slidingWindowMap(s));
            System.out.println("bruteForce:          " + sol.bruteForce(s));
            System.out.println("slidingWindowQueue:  " + sol.slidingWindowQueue(s));
            System.out.println("slidingWindowAscii:  " + sol.slidingWindowAscii(s));
            System.out.println("-----");
        }
    }
}
