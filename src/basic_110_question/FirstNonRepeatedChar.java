package basic_110_question;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FirstNonRepeatedChar {
    
    // Solution 1: Using LinkedHashMap (Preserves insertion order)
    public static Character findFirstNonRepeatedUsingLinkedHashMap(String str) {
        Map<Character, Integer> charCount = new LinkedHashMap<>();
        
        for (char c : str.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }
        
        for (Map.Entry<Character, Integer> entry : charCount.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        
        return null;
    }
    
    // Solution 2: Using Two Passes with HashMap
    public static Character findFirstNonRepeatedTwoPass(String str) {
        Map<Character, Integer> charCount = new HashMap<>();
        
        // First pass: count frequencies
        for (char c : str.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }
        
        // Second pass: find first character with count 1
        for (char c : str.toCharArray()) {
            if (charCount.get(c) == 1) {
                return c;
            }
        }
        
        return null;
    }
    
    // Solution 3: Using Java 8 Streams
    public static Character findFirstNonRepeatedUsingStreams(String str) {
        return str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }
    
    // Solution 4: Using IndexOf and LastIndexOf
    public static Character findFirstNonRepeatedUsingIndex(String str) {
        for (char c : str.toCharArray()) {
            if (str.indexOf(c) == str.lastIndexOf(c)) {
                return c;
            }
        }
        return null;
    }
    
    // Solution 5: Using Set for Repeated Characters
    public static Character findFirstNonRepeatedUsingSet(String str) {
        Set<Character> repeated = new HashSet<>();
        Set<Character> nonRepeated = new LinkedHashSet<>();
        
        for (char c : str.toCharArray()) {
            if (repeated.contains(c)) {
                continue;
            }
            if (nonRepeated.contains(c)) {
                nonRepeated.remove(c);
                repeated.add(c);
            } else {
                nonRepeated.add(c);
            }
        }
        
        return nonRepeated.isEmpty() ? null : nonRepeated.iterator().next();
    }
    
    // Solution 6: Using Array for ASCII (Most Efficient for ASCII)
    public static Character findFirstNonRepeatedUsingASCII(String str) {
        int[] charCount = new int[256];
        
        // Count frequencies
        for (char c : str.toCharArray()) {
            charCount[c]++;
        }
        
        // Find first non-repeated
        for (char c : str.toCharArray()) {
            if (charCount[c] == 1) {
                return c;
            }
        }
        
        return null;
    }
    
    public static void main(String[] args) {
        String test = "swiss";
        System.out.println("Test String: " + test);
        
        System.out.println("\n1. Using LinkedHashMap: " + findFirstNonRepeatedUsingLinkedHashMap(test));
        System.out.println("2. Two Pass HashMap: " + findFirstNonRepeatedTwoPass(test));
        System.out.println("3. Using Streams: " + findFirstNonRepeatedUsingStreams(test));
        System.out.println("4. Using IndexOf: " + findFirstNonRepeatedUsingIndex(test));
        System.out.println("5. Using Set: " + findFirstNonRepeatedUsingSet(test));
        System.out.println("6. Using ASCII Array: " + findFirstNonRepeatedUsingASCII(test));
        
        // Test with different cases
        System.out.println("\n--- Additional Test Cases ---");
        String[] tests = {"hello", "aabbcc", "javaj", "stream", "aabbccddee"};
        for (String t : tests) {
            System.out.println(t + " -> " + findFirstNonRepeatedUsingLinkedHashMap(t));
        }
    }
}