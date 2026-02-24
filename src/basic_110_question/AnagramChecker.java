package basic_110_question;

import java.util.*;
import java.util.stream.Collectors;

public class AnagramChecker {
    
    // Solution 1: Using Sorting
    public static boolean areAnagramsUsingSorting(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
        
        char[] charArray1 = str1.toLowerCase().toCharArray();
        char[] charArray2 = str2.toLowerCase().toCharArray();
        
        Arrays.sort(charArray1);
        Arrays.sort(charArray2);
        
        return Arrays.equals(charArray1, charArray2);
    }
    
    // Solution 2: Using Character Count Array
    public static boolean areAnagramsUsingCountArray(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
        
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        
        int[] count = new int[26]; // for lowercase letters only
        
        for (int i = 0; i < str1.length(); i++) {
            count[str1.charAt(i) - 'a']++;
            count[str2.charAt(i) - 'a']--;
        }
        
        for (int c : count) {
            if (c != 0) {
                return false;
            }
        }
        
        return true;
    }
    
    // Solution 3: Using HashMap
    public static boolean areAnagramsUsingMap(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
        
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        
        Map<Character, Integer> map = new HashMap<>();
        
        for (char c : str1.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        
        for (char c : str2.toCharArray()) {
            if (!map.containsKey(c)) {
                return false;
            }
            map.put(c, map.get(c) - 1);
            if (map.get(c) == 0) {
                map.remove(c);
            }
        }
        
        return map.isEmpty();
    }
    
    // Solution 4: Using Java 8 Streams
    public static boolean areAnagramsUsingStreams(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
        
        String sorted1 = str1.toLowerCase().chars()
                .sorted()
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.joining());
        
        String sorted2 = str2.toLowerCase().chars()
                .sorted()
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.joining());
        
        return sorted1.equals(sorted2);
    }
    
    // Solution 5: Using StringBuilder
    public static boolean areAnagramsUsingStringBuilder(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
        
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        
        StringBuilder sb = new StringBuilder(str2);
        
        for (char c : str1.toCharArray()) {
            int index = sb.indexOf(String.valueOf(c));
            if (index == -1) {
                return false;
            }
            sb.deleteCharAt(index);
        }
        
        return sb.length() == 0;
    }
    
    // Solution 6: Using Multiset (Guava style - implemented manually)
    public static boolean areAnagramsUsingMultiset(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
        
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        
        Map<Character, Integer> multiset1 = new HashMap<>();
        Map<Character, Integer> multiset2 = new HashMap<>();
        
        for (char c : str1.toCharArray()) {
            multiset1.put(c, multiset1.getOrDefault(c, 0) + 1);
        }
        
        for (char c : str2.toCharArray()) {
            multiset2.put(c, multiset2.getOrDefault(c, 0) + 1);
        }
        
        return multiset1.equals(multiset2);
    }
    
    public static void main(String[] args) {
        String s1 = "listen";
        String s2 = "silent";
        
        System.out.println("String1: " + s1 + ", String2: " + s2);
        System.out.println("\n1. Using Sorting: " + areAnagramsUsingSorting(s1, s2));
        System.out.println("2. Using Count Array: " + areAnagramsUsingCountArray(s1, s2));
        System.out.println("3. Using HashMap: " + areAnagramsUsingMap(s1, s2));
        System.out.println("4. Using Streams: " + areAnagramsUsingStreams(s1, s2));
        System.out.println("5. Using StringBuilder: " + areAnagramsUsingStringBuilder(s1, s2));
        System.out.println("6. Using Multiset: " + areAnagramsUsingMultiset(s1, s2));
    }
}