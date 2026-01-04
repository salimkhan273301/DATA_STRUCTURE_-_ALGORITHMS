package generalproblem;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class IsAnagram {
	
	 // 10. Using Multiset (via Java Map simulation)
		/*
		 * public static boolean areAnagrams10(String s1, String s2) { if (s1.length()
		 * != s2.length()) return false; return new TreeMap<>(s1.chars().boxed()
		 * .collect(Collectors.groupingBy(e -> e, TreeMap::new, Collectors.counting())))
		 * .equals(new TreeMap<>(s2.chars().boxed() .collect(Collectors.groupingBy(e ->
		 * e, TreeMap::new, Collectors.counting())))); }
		 */

	 // 9. Character Count Difference (One array update)
    public static boolean areAnagrams9(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        int[] freq = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            freq[s1.charAt(i) - 'a']++;
            freq[s2.charAt(i) - 'a']--;
        }
        for (int f : freq) if (f != 0) return false;
        return true;
    }
	
	
	  // 6. XOR Character Sum Trick
    // Works because XOR cancels out same characters
    public static boolean areAnagrams6(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        int xor = 0;
        for (char c : s1.toCharArray()) xor ^= c;
        for (char c : s2.toCharArray()) xor ^= c;
        return xor == 0;
    }
		

	    // 1. Frequency Array (Only lowercase letters a-z)
	    public static boolean areAnagrams1(String s1, String s2) {
	        if (s1.length() != s2.length()) return false;
	        int freq[] = new int[26];
	        for (char c : s1.toCharArray()) freq[c - 'a']++;
	        for (char c : s2.toCharArray()) freq[c - 'a']--;
	        for (int f : freq) if (f != 0) return false;
	        return true;
	    }

	    // 2. Sorting both strings
	    public static boolean areAnagrams2(String s1, String s2) {
	        if (s1.length() != s2.length()) return false;
	        char[] a1 = s1.toCharArray();
	        char[] a2 = s2.toCharArray();
	        Arrays.sort(a1);
	        Arrays.sort(a2);
	        return Arrays.equals(a1, a2);
	    }

	    // 3. HashMap Frequency Counter
	    public static boolean areAnagrams3(String s1, String s2) {
	        if (s1.length() != s2.length()) return false;
	        Map<Character, Integer> map = new HashMap<>();
	        for (char c : s1.toCharArray()) {
	            map.put(c, map.getOrDefault(c, 0) + 1);
	        }
	        for (char c : s2.toCharArray()) {
	            if (!map.containsKey(c)) return false;
	            map.put(c, map.get(c) - 1);
	            if (map.get(c) == 0) map.remove(c);
	        }
	        return map.isEmpty();
	    }

	    // 4. Using Streams & Grouping (Java 8+)
	    public static boolean areAnagrams4(String s1, String s2) {
	        if (s1.length() != s2.length()) return false;
	        Map<Integer, Long> freq1 = s1.chars()
	                .boxed()
	                .collect(Collectors.groupingBy(e -> e, Collectors.counting()));
	        Map<Integer, Long> freq2 = s2.chars()
	                .boxed()
	                .collect(Collectors.groupingBy(e -> e, Collectors.counting()));
	        return freq1.equals(freq2);
	    }

	    // 5. Prime Number Product Method (Cool Trick ⚡)
	    // Assign a unique prime to each letter, multiply them -> same product => anagram
	    public static boolean areAnagrams5(String s1, String s2) {
	        if (s1.length() != s2.length()) return false;
	        int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 
	                        31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 
	                        73, 79, 83, 89, 97, 101}; // 26 primes
	        long prod1 = 1, prod2 = 1;
	        for (char c : s1.toCharArray()) prod1 *= primes[c - 'a'];
	        for (char c : s2.toCharArray()) prod2 *= primes[c - 'a'];
	        return prod1 == prod2;
	    }

	    // Quick test
	    public static void main(String[] args) {
	        String[][] tests = {
	            {"listen", "silent"},
	            {"triangle", "integral"},
	            {"hello", "world"},
	            {"abc", "cab"},
	            {"rat", "car"}
	        };

	        for (String[] pair : tests) {
	            System.out.println("Testing: " + Arrays.toString(pair));
	            System.out.println("areAnagrams1: " + areAnagrams1(pair[0], pair[1]));
	            System.out.println("areAnagrams2: " + areAnagrams2(pair[0], pair[1]));
	            System.out.println("areAnagrams3: " + areAnagrams3(pair[0], pair[1]));
	            System.out.println("areAnagrams4: " + areAnagrams4(pair[0], pair[1]));
	            System.out.println("areAnagrams5: " + areAnagrams5(pair[0], pair[1]));
	            System.out.println("----");
	        }
	    }
	


}
