package algoBasedSolution;

class Solution {
    public int minChar(String s) {
        int n = s.length();
        String reversed = new StringBuilder(s).reverse().toString();
        String combined = s + "#" + reversed;
        int[] lpsArray = computeLPS(combined);
        
        return n - lpsArray[lpsArray.length - 1];
    }
    
    public static int[] computeLPS(String s) {
        int n = s.length();
        int[] lps = new int[n];
        int prefixLength = 0, currentIndex = 1;
        
        while (currentIndex < n) {
            if (s.charAt(prefixLength) == s.charAt(currentIndex)) {
                lps[currentIndex] = prefixLength + 1;
                currentIndex++;
                prefixLength++;
            } else {
                if (prefixLength == 0) {
                    lps[currentIndex] = 0;
                    currentIndex++;
                } else {
                    prefixLength = lps[prefixLength - 1];
                }
            }
        }
        return lps;
    }
}

public class KMLAlgo {
    public int minChar(String s) {
        int n = s.length();
        String reversed = new StringBuilder(s).reverse().toString();
        String combined = s + "#" + reversed;
        int[] lps = computeLPS(combined);
        return n - lps[lps.length - 1];
    }
    
    private int[] computeLPS(String str) {
        int n = str.length();
        int[] lps = new int[n];
        int len = 0, i = 1;
        
        while (i < n) {
            if (str.charAt(i) == str.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }
}