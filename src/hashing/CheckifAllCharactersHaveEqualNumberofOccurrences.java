package hashing;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CheckifAllCharactersHaveEqualNumberofOccurrences {


	    // 1) Array frequency approach (Correct version)
	    public static boolean areOccurrencesEqual_Array(String s) {
	        int[] freq = new int[26];

	        for (char c : s.toCharArray())
	            freq[c - 'a']++;

	        int freqFirst = 0;
	        for (int f : freq) {
	            if (f > 0) {
	                freqFirst = f;
	                break;
	            }
	        }

	        for (int f : freq) {
	            if (f > 0 && f != freqFirst) return false;
	        }
	        return true;
	    }

	    // 2) HashMap approach
	    public static boolean areOccurrencesEqual_Map(String s) {
	        Map<Character, Integer> map = new HashMap<>();
	        for (char c : s.toCharArray())
	            map.put(c, map.getOrDefault(c, 0) + 1);

	        int freq = map.get(s.charAt(0));
	        for (int val : map.values())
	            if (val != freq) return false;

	        return true;
	    }

	    // 3) Streams API approach (one-liner)
	    public static boolean areOccurrencesEqual_Stream(String s) {
	        Map<Character, Long> freq = s.chars()
	                .mapToObj(c -> (char) c)
	                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));

	        return freq.values().stream().distinct().count() == 1;
	    }

	    // 4) Bitmask optimized approach (fastest)
	    public static boolean areOccurrencesEqual_BitMask(String s) {
	        int[] freq = new int[26];
	        for (char c : s.toCharArray())
	            freq[c - 'a']++;

	        int equalMask = (1 << freq[s.charAt(0) - 'a']);
	        for (int f : freq)
	            if (f > 0 && (equalMask != (1 << f))) return false;

	        return true;
	    }

	    // ------------------------------------------------------------------
	    // TEST DRIVER
	    public static void main(String[] args) {
	        String[] tests = {
	                "abacbc",     // true
	                "aabbcc",     // true
	                "bbccc",      // false
	                "aaaa",       // true
	                "abc",        // true
	                "aabbccd",    // false
	                "zzzyyyxxx",  // true
	                "a",          // true
	                "abababccc",  // false
	        };

	        System.out.println("=========== TEST RESULTS ===========");
	        for (String s : tests) {
	            System.out.printf("Input: %-12s  Array=%-5s Map=%-5s Stream=%-5s BitMask=%-5s\n",
	                    s,
	                    areOccurrencesEqual_Array(s),
	                    areOccurrencesEqual_Map(s),
	                    areOccurrencesEqual_Stream(s),
	                    areOccurrencesEqual_BitMask(s)
	            );
	        }
	    }
	

}
