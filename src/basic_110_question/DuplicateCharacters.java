package basic_110_question;

import java.util.*;
import java.util.stream.Collectors;

public class DuplicateCharacters {
    
    // Solution 1: Using HashMap (Basic)
    public static Map<Character, Integer> findDuplicatesUsingMap(String str) {
        Map<Character, Integer> charCount = new HashMap<>();
        Map<Character, Integer> duplicates = new HashMap<>();
        
        for (char c : str.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }
        
        for (Map.Entry<Character, Integer> entry : charCount.entrySet()) {
            if (entry.getValue() > 1) {
                duplicates.put(entry.getKey(), entry.getValue());
            }
        }
        
        return duplicates;
    }
    
    // Solution 2: Using Java 8 Streams
    public static Map<Character, Long> findDuplicatesUsingStreams(String str) {
        return str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
    
    // Solution 3: Using ASCII Array (for ASCII characters only)
    public static List<Character> findDuplicatesUsingASCII(String str) {
        int[] ascii = new int[256];
        List<Character> duplicates = new ArrayList<>();
        
        for (char c : str.toCharArray()) {
            ascii[c]++;
        }
        
        for (int i = 0; i < 256; i++) {
            if (ascii[i] > 1) {
                duplicates.add((char) i);
            }
        }
        
        return duplicates;
    }
    
    // Solution 4: Using Set (Finds duplicates without count)
    public static Set<Character> findDuplicatesUsingSet(String str) {
        Set<Character> seen = new HashSet<>();
        Set<Character> duplicates = new HashSet<>();
        
        for (char c : str.toCharArray()) {
            if (!seen.add(c)) {
                duplicates.add(c);
            }
        }
        
        return duplicates;
    }
    
    // Solution 5: Using Sorting
    public static List<Character> findDuplicatesUsingSorting(String str) {
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        List<Character> duplicates = new ArrayList<>();
        
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == chars[i - 1] && 
                (duplicates.isEmpty() || duplicates.get(duplicates.size() - 1) != chars[i])) {
                duplicates.add(chars[i]);
            }
        }
        
        return duplicates;
    }
    
    // Solution 6: Using LinkedHashMap (Preserves order)
    public static Map<Character, Integer> findDuplicatesPreservingOrder(String str) {
        Map<Character, Integer> charCount = new LinkedHashMap<>();
        Map<Character, Integer> duplicates = new LinkedHashMap<>();
        
        for (char c : str.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }
        
        for (Map.Entry<Character, Integer> entry : charCount.entrySet()) {
            if (entry.getValue() > 1) {
                duplicates.put(entry.getKey(), entry.getValue());
            }
        }
        
        return duplicates;
    }
    
    public static void main(String[] args) {
        String test = "programming";
        System.out.println("Test String: " + test);
        
        System.out.println("\n1. Using HashMap: " + findDuplicatesUsingMap(test));
        System.out.println("2. Using Streams: " + findDuplicatesUsingStreams(test));
        System.out.println("3. Using ASCII Array: " + findDuplicatesUsingASCII(test));
        System.out.println("4. Using Set: " + findDuplicatesUsingSet(test));
        System.out.println("5. Using Sorting: " + findDuplicatesUsingSorting(test));
        System.out.println("6. Preserving Order: " + findDuplicatesPreservingOrder(test));
    }
}