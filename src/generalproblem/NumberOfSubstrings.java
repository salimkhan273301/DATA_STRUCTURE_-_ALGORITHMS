package generalproblem;

public class NumberOfSubstrings {

    // Approach 1: Brute Force Check
    public int numOfStrings1(String[] patterns, String word) {
        int count = 0;
        for (String pattern : patterns) {
            if (word.contains(pattern)) {
                count++;
            }
        }
        return count;
    }

    // Approach 2: Using indexOf Method
    public int numOfStrings2(String[] patterns, String word) {
        int count = 0;
        for (String pattern : patterns) {
            if (word.indexOf(pattern) != -1) {
                count++;
            }
        }
        return count;
    }

    // Approach 3: Using String's matches Method (with regex)
    public int numOfStrings3(String[] patterns, String word) {
        int count = 0;
        for (String pattern : patterns) {
            if (word.matches(".*" + pattern + ".*")) {
                count++;
            }
        }
        return count;
    }

    // Approach 4: Using String's regionMatches Method
    public int numOfStrings4(String[] patterns, String word) {
        int count = 0;
        for (String pattern : patterns) {
            int patternLength = pattern.length();
            int wordLength = word.length();
            if (patternLength > wordLength) {
                continue;
            }
            boolean found = false;
            for (int i = 0; i <= wordLength - patternLength; i++) {
                if (word.regionMatches(i, pattern, 0, patternLength)) {
                    found = true;
                    break;
                }
            }
            if (found) {
                count++;
            }
        }
        return count;
    }

    // Approach 5: Using KMP Algorithm for Substring Search
    public int numOfStrings5(String[] patterns, String word) {
        int count = 0;
        for (String pattern : patterns) {
            if (kmpSearch(word, pattern)) {
                count++;
            }
        }
        return count;
    }

    private boolean kmpSearch(String text, String pattern) {
        int[] lps = computeLPSArray(pattern);
        int i = 0; // index for text
        int j = 0; // index for pattern
        while (i < text.length()) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
                if (j == pattern.length()) {
                    return true;
                }
            } else {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        return false;
    }

    private int[] computeLPSArray(String pattern) {
        int[] lps = new int[pattern.length()];
        int len = 0;
        int i = 1;
        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
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

    // Test Cases
    public static void main(String[] args) {
        NumberOfSubstrings nos = new NumberOfSubstrings();
        String[][] testCases = {
            {"a", "abc"}, // Expected: 1
            {"a", "b"},   // Expected: 0
            {"a", "a"},    // Expected: 1
            {"a", "aa"},   // Expected: 1
            {"aa", "a"},   // Expected: 0
            {"aa", "aa"},  // Expected: 1
            {"abc", "abcabc"}, // Expected: 1
            {"abc", "ab"},     // Expected: 0
            {"a", "aaa"},       // Expected: 1
            {"ab", "aab"}       // Expected: 1
        };

        for (int i = 0; i < testCases.length; i++) {
            String[] patterns = {testCases[i][0]};
            String word = testCases[i][1];
            int expected = i == 5 ? 1 : (i == 1 || i == 4 || i == 7 ? 0 : 1); // Manually set expected values based on test cases
            int result1 = nos.numOfStrings1(patterns, word);
            int result2 = nos.numOfStrings2(patterns, word);
            int result3 = nos.numOfStrings3(patterns, word);
            int result4 = nos.numOfStrings4(patterns, word);
            int result5 = nos.numOfStrings5(patterns, word);

            System.out.println("Test Case " + (i + 1) + ": patterns = [" + patterns[0] + "], word = " + word);
            System.out.println("Expected: " + expected);
            System.out.println("Approach 1: " + result1 + " " + (result1 == expected ? "✓" : "✗"));
            System.out.println("Approach 2: " + result2 + " " + (result2 == expected ? "✓" : "✗"));
            System.out.println("Approach 3: " + result3 + " " + (result3 == expected ? "✓" : "✗"));
            System.out.println("Approach 4: " + result4 + " " + (result4 == expected ? "✓" : "✗"));
            System.out.println("Approach 5: " + result5 + " " + (result5 == expected ? "✓" : "✗"));
            System.out.println();
        }
    }
}