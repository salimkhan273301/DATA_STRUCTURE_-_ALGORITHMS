package basic_110_question;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CharacterCount {
    
    // Solution 1: Using HashMap
    public static Map<Character, Integer> countUsingHashMap(String str) {
        Map<Character, Integer> charCount = new HashMap<>();
        
        for (char c : str.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }
        
        return charCount;
    }
    
    // Solution 2: Using Java 8 Streams
    public static Map<Character, Long> countUsingStreams(String str) {
        return str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
    
    // Solution 3: Using TreeMap (Sorted Order)
    public static Map<Character, Integer> countUsingTreeMap(String str) {
        Map<Character, Integer> charCount = new TreeMap<>();
        
        for (char c : str.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }
        
        return charCount;
    }
    
    // Solution 4: Using ASCII Array (Most Efficient)
    public static int[] countUsingASCII(String str) {
        int[] count = new int[256];
        
        for (char c : str.toCharArray()) {
            count[c]++;
        }
        
        return count;
    }
    
    // Solution 5: Using Multiset Pattern with Collections
    public static Map<Character, Integer> countUsingCollectionsFrequency(String str) {
        List<Character> chars = new ArrayList<>();
        for (char c : str.toCharArray()) {
            chars.add(c);
        }
        
        Set<Character> uniqueChars = new HashSet<>(chars);
        Map<Character, Integer> charCount = new HashMap<>();
        
        for (char c : uniqueChars) {
            charCount.put(c, Collections.frequency(chars, c));
        }
        
        return charCount;
    }
    
    // Solution 6: Using LinkedHashMap (Preserves Insertion Order)
    public static Map<Character, Integer> countUsingLinkedHashMap(String str) {
        Map<Character, Integer> charCount = new LinkedHashMap<>();
        
        for (char c : str.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }
        
        return charCount;
    }
    
    // Helper method to print ASCII array results
    public static void printASCIICount(int[] count, String str) {
        System.out.println("ASCII Array Results:");
        Set<Character> printed = new HashSet<>();
        for (char c : str.toCharArray()) {
            if (!printed.contains(c) && count[c] > 0) {
                System.out.println("'" + c + "': " + count[c]);
                printed.add(c);
            }
        }
    }
    
    public static void main(String[] args) {
        String test = "hello world";
        System.out.println("Test String: \"" + test + "\"");
        
        System.out.println("\n1. Using HashMap:");
        System.out.println(countUsingHashMap(test));
        
        System.out.println("\n2. Using Streams:");
        System.out.println(countUsingStreams(test));
        
        System.out.println("\n3. Using TreeMap (Sorted):");
        System.out.println(countUsingTreeMap(test));
        
        System.out.println("\n4. Using ASCII Array:");
        int[] asciiCount = countUsingASCII(test);
        printASCIICount(asciiCount, test);
        
        System.out.println("\n5. Using Collections.frequency:");
        System.out.println(countUsingCollectionsFrequency(test));
        
        System.out.println("\n6. Using LinkedHashMap (Insertion Order):");
        System.out.println(countUsingLinkedHashMap(test));
        
        // Performance comparison for large string
        System.out.println("\n--- Performance Test ---");
        String largeTest = "abcdefghijklmnopqrstuvwxyz".repeat(1000);
        
        long startTime = System.nanoTime();
        countUsingASCII(largeTest);
        long endTime = System.nanoTime();
        System.out.println("ASCII Array time: " + (endTime - startTime) + " ns");
        
        startTime = System.nanoTime();
        countUsingHashMap(largeTest);
        endTime = System.nanoTime();
        System.out.println("HashMap time: " + (endTime - startTime) + " ns");
    }
}