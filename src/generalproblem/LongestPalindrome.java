package generalproblem;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class LongestPalindrome {
	
	public int longestPalindromeMySol(String s) {
		
		Map<Character,Integer> freq=s.chars().mapToObj(e->(char)e).collect(Collectors.toMap(e->e, e->1,Integer::sum));
		
		int oddCount=(int) freq.values().stream().filter(e->e==1).count();
		
		return oddCount>1?s.length()-oddCount+1:s.length();
		
	}
	

    // Solution 1: Greedy Approach with Character Counting (Most Efficient)
    // Count all characters, add even counts fully, add (odd count - 1), and add 1 if any odd exists
    public int longestPalindrome1(String s) {
        int[] charCounts = new int[128]; // Covers all ASCII characters
        for (char c : s.toCharArray()) {
            charCounts[c]++; // Count frequency of each character
        }
        
        int length = 0;
        boolean oddFound = false;
        
        for (int count : charCounts) {
            if (count % 2 == 0) {
                length += count; // Use all even counts
            } else {
                length += count - 1; // Use even part of odd counts
                oddFound = true; // Mark that we have at least one odd
            }
        }
        
        // Add 1 for the center character if any odd counts were found
        return oddFound ? length + 1 : length;
    }

    // Solution 2: Using Frequency Map (Similar to Python's Counter)
    // Count odds, result is total length minus odds plus 1 (if any odds exist)
    public int longestPalindrome2(String s) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : s.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1); // Build frequency map
        }
        
        int odds = 0;
        for (int count : freq.values()) {
            if (count % 2 != 0) {
                odds++; // Count how many characters have odd frequencies
            }
        }
        
        // All characters except (odds - 1) can be used, plus 1 center if needed
        return odds > 0 ? s.length() - odds + 1 : s.length();
    }

    // Solution 3: Using Set for Pair Tracking
    // Track pairs using set - when we see a character twice, it forms a pair
    public int longestPalindrome3(String s) {
        Set<Character> unpaired = new HashSet<>();
        int pairs = 0;
        
        for (char c : s.toCharArray()) {
            if (unpaired.contains(c)) {
                unpaired.remove(c);
                pairs++; // Found a pair
            } else {
                unpaired.add(c); // Add to unpaired set
            }
        }
        
        // Each pair contributes 2, plus 1 if any unpaired characters remain
        return pairs * 2 + (unpaired.isEmpty() ? 0 : 1);
    }

    // Solution 4: Bit Manipulation Approach (Advanced)
    // Toggle bits for each character, count set bits for odd counts
    public int longestPalindrome4(String s) {
        int bitmask = 0;
        for (char c : s.toCharArray()) {
            bitmask ^= 1 << (c - 'A'); // Toggle bit for each character
        }
        
        int oddCounts = Integer.bitCount(bitmask); // Count how many bits are set (odd counts)
        // All characters except (oddCounts - 1) can be used, plus 1 center if needed
        return oddCounts > 0 ? s.length() - oddCounts + 1 : s.length();
    }

    // Solution 5: Simple Frequency Array (Optimized for Java)
    // Similar to Solution 1 but with slightly different implementation
    public int longestPalindrome5(String s) {
        int[] freq = new int[128]; // ASCII frequency array
        for (char c : s.toCharArray()) {
            freq[c]++; // Count each character
        }
        
        int length = 0;
        boolean hasOdd = false;
        
        for (int count : freq) {
            length += (count / 2) * 2; // Add even part of counts
            if (count % 2 == 1) {
                hasOdd = true; // Mark if any odd exists
            }
        }
        
        // Add 1 for center character if any odds were found
        return hasOdd ? length + 1 : length;
    }

    // Test cases
    public static void main(String[] args) {
        LongestPalindrome lp = new LongestPalindrome();
        String[] testCases = {
            "abccccdd", // Expected: 7 ("dccaccd")
            "a",         // Expected: 1 ("a")
            "bb",        // Expected: 2 ("bb")
            "abc",       // Expected: 1 ("a", "b", or "c")
            "Aa",        // Expected: 1 ("A" or "a")
            "ccc",      // Expected: 3 ("ccc")
            "ababab"     // Expected: 6 ("ababba" or similar)
        };
        
        int[] expected = {7, 1, 2, 1, 1, 3, 6};
        
        for (int i = 0; i < testCases.length; i++) {
            System.out.println("Test Case " + (i+1) + ": \"" + testCases[i] + "\"");
            System.out.println("Solution 1: " + lp.longestPalindrome1(testCases[i]) + 
                             " (Expected: " + expected[i] + ")");
            System.out.println("Solution 2: " + lp.longestPalindrome2(testCases[i]) + 
                             " (Expected: " + expected[i] + ")");
            System.out.println("Solution 3: " + lp.longestPalindrome3(testCases[i]) + 
                             " (Expected: " + expected[i] + ")");
            System.out.println("Solution 4: " + lp.longestPalindrome4(testCases[i]) + 
                             " (Expected: " + expected[i] + ")");
            System.out.println("Solution 5: " + lp.longestPalindrome5(testCases[i]) + 
                             " (Expected: " + expected[i] + ")");
            System.out.println();
        }
    }
}