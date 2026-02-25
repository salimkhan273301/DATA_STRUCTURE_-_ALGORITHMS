package basic_110_question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StringPermutations {
    
    // Solution 1: Recursive Approach (Swap based)
    public static List<String> findPermutationsRecursive(String str) {
        List<String> permutations = new ArrayList<>();
        if (str == null || str.isEmpty()) return permutations;
        
        char[] chars = str.toCharArray();
        generatePermutationsRecursive(chars, 0, permutations);
        return permutations;
    }
    
    private static void generatePermutationsRecursive(char[] chars, int index, List<String> result) {
        if (index == chars.length - 1) {
            result.add(new String(chars));
            return;
        }
        
        for (int i = index; i < chars.length; i++) {
            swap(chars, index, i);
            generatePermutationsRecursive(chars, index + 1, result);
            swap(chars, index, i); // backtrack
        }
    }
    
    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }
    
    // Solution 2: Using Backtracking with StringBuilder
    public static List<String> findPermutationsBacktracking(String str) {
        List<String> result = new ArrayList<>();
        boolean[] used = new boolean[str.length()];
        backtrack(str, new StringBuilder(), used, result);
        return result;
    }
    
    private static void backtrack(String str, StringBuilder current, boolean[] used, List<String> result) {
        if (current.length() == str.length()) {
            result.add(current.toString());
            return;
        }
        
        for (int i = 0; i < str.length(); i++) {
            if (used[i]) continue;
            
            // Skip duplicates (if string has duplicate characters)
            if (i > 0 && str.charAt(i) == str.charAt(i - 1) && !used[i - 1]) {
                continue;
            }
            
            used[i] = true;
            current.append(str.charAt(i));
            backtrack(str, current, used, result);
            current.deleteCharAt(current.length() - 1);
            used[i] = false;
        }
    }
    
    // Solution 3: Using Heap's Algorithm (Iterative)
    public static List<String> findPermutationsHeaps(String str) {
        List<String> permutations = new ArrayList<>();
        char[] chars = str.toCharArray();
        int n = chars.length;
        
        int[] c = new int[n];
        
        permutations.add(new String(chars));
        
        int i = 0;
        while (i < n) {
            if (c[i] < i) {
                if (i % 2 == 0) {
                    swap(chars, 0, i);
                } else {
                    swap(chars, c[i], i);
                }
                permutations.add(new String(chars));
                c[i]++;
                i = 0;
            } else {
                c[i] = 0;
                i++;
            }
        }
        
        return permutations;
    }
    
    // Solution 4: Using Collections2 (if using Guava - implemented manually here)
    public static List<String> findPermutationsUsingQueue(String str) {
        List<String> permutations = new ArrayList<>();
        if (str.length() == 0) return permutations;
        
        Queue<String> queue = new LinkedList<>();
        queue.add("");
        
        for (char c : str.toCharArray()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String current = queue.poll();
                for (int j = 0; j <= current.length(); j++) {
                    String newPerm = current.substring(0, j) + c + current.substring(j);
                    if (newPerm.length() == str.length()) {
                        permutations.add(newPerm);
                    } else {
                        queue.add(newPerm);
                    }
                }
            }
        }
        
        return permutations.stream().distinct().collect(Collectors.toList());
    }
    
    // Solution 5: Using Next Permutation Algorithm (for distinct permutations in lex order)
    public static List<String> findPermutationsNextPermutation(String str) {
        List<String> permutations = new ArrayList<>();
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        
        do {
            permutations.add(new String(chars));
        } while (nextPermutation(chars));
        
        return permutations;
    }
    
    private static boolean nextPermutation(char[] chars) {
        int i = chars.length - 2;
        while (i >= 0 && chars[i] >= chars[i + 1]) {
            i--;
        }
        
        if (i < 0) return false;
        
        int j = chars.length - 1;
        while (chars[j] <= chars[i]) {
            j--;
        }
        
        swap(chars, i, j);
        reverse(chars, i + 1, chars.length - 1);
        return true;
    }
    
    private static void reverse(char[] chars, int start, int end) {
        while (start < end) {
            swap(chars, start, end);
            start++;
            end--;
        }
    }
    
    // Solution 6: Using Streams with Recursion
    public static List<String> findPermutationsStreams(String str) {
        if (str.length() <= 1) {
            return Arrays.asList(str);
        }
        
        return IntStream.range(0, str.length())
                .boxed()
                .flatMap(i -> {
                    char c = str.charAt(i);
                    String remaining = str.substring(0, i) + str.substring(i + 1);
                    return findPermutationsStreams(remaining).stream()
                            .map(p -> c + p);
                })
                .collect(Collectors.toList());
    }
    
    // Count permutations with duplicates
    public static long countPermutationsWithDuplicates(String str) {
        Map<Character, Long> frequency = str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
        
        long numerator = factorial(str.length());
        long denominator = frequency.values().stream()
                .mapToLong(f -> factorial(f.intValue()))
                .reduce(1, (a, b) -> a * b);
        
        return numerator / denominator;
    }
    
    private static long factorial(int n) {
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
    
    public static void main(String[] args) {
        String[] testStrings = {"ABC", "AB", "A", "ABAB"};
        
        for (String str : testStrings) {
            System.out.println("\n--- Permutations of \"" + str + "\" ---");
            
            System.out.println("1. Recursive (Swap):");
            List<String> perms1 = findPermutationsRecursive(str);
            System.out.println("   Count: " + perms1.size());
            if (str.length() <= 3) System.out.println("   " + perms1);
            
            System.out.println("2. Backtracking:");
            List<String> perms2 = findPermutationsBacktracking(str);
            System.out.println("   Count: " + perms2.size());
            
            System.out.println("3. Heap's Algorithm:");
            List<String> perms3 = findPermutationsHeaps(str);
            System.out.println("   Count: " + perms3.size());
            
            System.out.println("4. Using Queue:");
            List<String> perms4 = findPermutationsUsingQueue(str);
            System.out.println("   Count: " + perms4.size());
            
            System.out.println("5. Next Permutation (Lex order):");
            List<String> perms5 = findPermutationsNextPermutation(str);
            System.out.println("   Count: " + perms5.size());
            if (str.length() <= 3) System.out.println("   " + perms5);
            
            System.out.println("6. Using Streams:");
            List<String> perms6 = findPermutationsStreams(str);
            System.out.println("   Count: " + perms6.size());
            
            System.out.println("Theoretical count (with duplicates): " + 
                              countPermutationsWithDuplicates(str));
        }
        
        // Performance test
        System.out.println("\n--- Performance Test (Permutations of \"ABCDE\") ---");
        long start = System.currentTimeMillis();
        List<String> perms = findPermutationsRecursive("ABCDE");
        long end = System.currentTimeMillis();
        System.out.println("Recursive method: " + (end - start) + " ms, Count: " + perms.size());
        
        start = System.currentTimeMillis();
        perms = findPermutationsNextPermutation("ABCDE");
        end = System.currentTimeMillis();
        System.out.println("Next Permutation method: " + (end - start) + " ms, Count: " + perms.size());
    }
}