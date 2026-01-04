package string;

import java.util.*;


public class AnagramSolutions {
    
    // Solution 1: Fixed Frequency Array (Best for lowercase English letters)
    // Time: O(n), Space: O(1)
    public boolean isAnagram1(String s, String t) {
        if (s.length() != t.length()) return false;
        
        int[] freq = new int[26];
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
            freq[t.charAt(i) - 'a']--;
        }
        
        for (int count : freq) {
            if (count != 0) return false;
        }
        return true;
    }
    
    // Solution 2: Two Separate Frequency Arrays
    // Time: O(n), Space: O(1)
    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) return false;
        
        int[] freqS = new int[26];
        int[] freqT = new int[26];
        
        for (char c : s.toCharArray()) freqS[c - 'a']++;
        for (char c : t.toCharArray()) freqT[c - 'a']++;
        
        for (int i = 0; i < 26; i++) {
            if (freqS[i] != freqT[i]) return false;
        }
        return true;
    }
    
    // Solution 3: Sorting Approach
    // Time: O(n log n), Space: O(n)
    public boolean isAnagram3(String s, String t) {
        if (s.length() != t.length()) return false;
        
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        
        Arrays.sort(sArr);
        Arrays.sort(tArr);
        
        return Arrays.equals(sArr, tArr);
    }
    
    // Solution 4: HashMap Approach (Good for any characters)
    // Time: O(n), Space: O(n)
    public boolean isAnagram4(String s, String t) {
        if (s.length() != t.length()) return false;
        
        Map<Character, Integer> map = new HashMap<>();
        
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        
        for (char c : t.toCharArray()) {
            if (!map.containsKey(c)) return false;
            map.put(c, map.get(c) - 1);
            if (map.get(c) == 0) map.remove(c);
        }
        
        return map.isEmpty();
    }
    
    // Solution 5: Unicode Support (Extended ASCII)
    // Time: O(n), Space: O(1)
    public boolean isAnagram5(String s, String t) {
        if (s.length() != t.length()) return false;
        
        int[] freq = new int[256]; // Extended ASCII support
        
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i)]++;
            freq[t.charAt(i)]--;
        }
        
        for (int count : freq) {
            if (count != 0) return false;
        }
        return true;
    }
    
    // Solution 6: Early Termination with Frequency
    // Time: O(n), Space: O(1)
    public boolean isAnagram6(String s, String t) {
        if (s.length() != t.length()) return false;
        
        int[] freq = new int[26];
        
        for (char c : s.toCharArray()) freq[c - 'a']++;
        
        for (char c : t.toCharArray()) {
            if (freq[c - 'a'] == 0) return false;
            freq[c - 'a']--;
        }
        
        return true;
    }
    
    // Solution 7: Using Streams (Java 8+)
    // Time: O(n log n), Space: O(n)
    public boolean isAnagram7(String s, String t) {
        if (s.length() != t.length()) return false;
        
        int[] sSorted = s.chars().sorted().toArray();
        int[] tSorted = t.chars().sorted().toArray();
        
        return Arrays.equals(sSorted, tSorted);
    }
    
    // Solution 8: Character Sum with Verification
    // Time: O(n), Space: O(1)
    public boolean isAnagram8(String s, String t) {
        if (s.length() != t.length()) return false;
        
        // Quick check using sum (not definitive)
        int sumS = 0, sumT = 0;
        for (char c : s.toCharArray()) sumS += c;
        for (char c : t.toCharArray()) sumT += c;
        if (sumS != sumT) return false;
        
        // Proper frequency verification
        int[] freq = new int[26];
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
            freq[t.charAt(i) - 'a']--;
        }
        
        for (int count : freq) {
            if (count != 0) return false;
        }
        return true;
    }
    
    // Solution 9: Bit Manipulation (Limited Use - Not for production)
    // Time: O(n), Space: O(1)
    public boolean isAnagram9(String s, String t) {
        if (s.length() != t.length()) return false;
        
        int check = 0;
        for (int i = 0; i < s.length(); i++) {
            check ^= s.charAt(i);
            check ^= t.charAt(i);
        }
        
        // This only works if characters appear in pairs
        return check == 0;
    }
    
    // Solution 10: Multi-threaded (Educational purpose only)
    // Time: O(n), Space: O(1)
    public boolean isAnagram10(String s, String t) {
        if (s.length() != t.length()) return false;
        
        int[] freq = new int[26];
        int mid = s.length() / 2;
        
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < mid; i++) {
                synchronized(freq) {
                    freq[s.charAt(i) - 'a']++;
                    freq[t.charAt(i) - 'a']--;
                }
            }
        });
        
        Thread t2 = new Thread(() -> {
            for (int i = mid; i < s.length(); i++) {
                synchronized(freq) {
                    freq[s.charAt(i) - 'a']++;
                    freq[t.charAt(i) - 'a']--;
                }
            }
        });
        
        t1.start();
        t2.start();
        
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
        
        for (int count : freq) {
            if (count != 0) return false;
        }
        return true;
    }
    
    // Utility method to test all solutions
    public void testAllSolutions(String s, String t) {
        System.out.println("Testing: s = \"" + s + "\", t = \"" + t + "\"");
        System.out.println("Solution 1: " + isAnagram1(s, t));
        System.out.println("Solution 2: " + isAnagram2(s, t));
        System.out.println("Solution 3: " + isAnagram3(s, t));
        System.out.println("Solution 4: " + isAnagram4(s, t));
        System.out.println("Solution 5: " + isAnagram5(s, t));
        System.out.println("Solution 6: " + isAnagram6(s, t));
        System.out.println("Solution 7: " + isAnagram7(s, t));
        System.out.println("Solution 8: " + isAnagram8(s, t));
        System.out.println("Solution 9: " + isAnagram9(s, t));
        System.out.println("Solution 10: " + isAnagram10(s, t));
        System.out.println("----------------------------------------");
    }
    
    // Main method for testing
    public static void main(String[] args) {
        AnagramSolutions solver = new AnagramSolutions();
        
        // Test cases
        solver.testAllSolutions("anagram", "nagaram"); // true
        solver.testAllSolutions("rat", "car");        // false
        solver.testAllSolutions("listen", "silent");  // true
        solver.testAllSolutions("hello", "world");    // false
        
        // Summary of recommendations:
        System.out.println("RECOMMENDATIONS:");
        System.out.println("1. For lowercase English letters: Use Solution 1 or 6");
        System.out.println("2. For Unicode/Extended ASCII: Use Solution 5");
        System.out.println("3. For any characters: Use Solution 4 (HashMap)");
        System.out.println("4. Avoid Solution 9 (Bit Manipulation) for production");
        System.out.println("5. Solution 10 is for educational purposes only");
    }
}