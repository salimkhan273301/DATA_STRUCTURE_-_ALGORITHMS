package generalproblem;

public class LongestUncommonSubsequence {

    // Approach 1: Check if strings are equal
    // Time Complexity: O(n), Space Complexity: O(1)
    public int findLUSlength1(String a, String b) {
        if (a.equals(b)) {
            return -1;
        }
        return Math.max(a.length(), b.length());
    }

    // Approach 2: Compare lengths directly
    // Time Complexity: O(1), Space Complexity: O(1)
    public int findLUSlength2(String a, String b) {
        return a.equals(b) ? -1 : Math.max(a.length(), b.length());
    }

    // Approach 3: Using substring check (inefficient but demonstrates the concept)
    // Time Complexity: O(n^2), Space Complexity: O(1)
    public int findLUSlength3(String a, String b) {
        if (a.length() > b.length()) {
            return a.length();
        } else if (b.length() > a.length()) {
            return b.length();
        } else {
            return a.equals(b) ? -1 : a.length();
        }
    }

    // Approach 4: Using String comparison with length check
    // Time Complexity: O(n), Space Complexity: O(1)
    public int findLUSlength4(String a, String b) {
        int lenA = a.length();
        int lenB = b.length();
        if (lenA != lenB) {
            return Math.max(lenA, lenB);
        } else {
            return a.equals(b) ? -1 : lenA;
        }
    }

    // Approach 5: Using Java Streams (for demonstration, not recommended for production)
    // Time Complexity: O(n), Space Complexity: O(1)
    public int findLUSlength5(String a, String b) {
        boolean isEqual = a.chars().boxed().toList().equals(b.chars().boxed().toList());
        return isEqual ? -1 : Math.max(a.length(), b.length());
    }

    // Test Cases
    public static void main(String[] args) {
        LongestUncommonSubsequence lus = new LongestUncommonSubsequence();
        String[][] testCases = {
            {"aba", "cdc"}, // Expected: 3
            {"aaa", "bbb"}, // Expected: 3
            {"aaa", "aaa"}, // Expected: -1
            {"abc", "def"}, // Expected: 3
            {"a", "b"},     // Expected: 1
            {"a", "a"},    // Expected: -1
            {"abc", "abcd"}, // Expected: 4
            {"abcd", "abc"}, // Expected: 4
            {"", ""},       // Expected: -1
            {"abc", "ab"}   // Expected: 3
        };

        for (int i = 0; i < testCases.length; i++) {
            String a = testCases[i][0];
            String b = testCases[i][1];
            int expected = -1;
            if (i == 0 || i == 1 || i == 3 || i == 4 || i == 6 || i == 7 || i == 9) {
                expected = i == 0 || i == 1 || i == 3 ? 3 : (i == 4 ? 1 : (i == 6 || i == 7 ? 4 : 3));
            } else if (i == 2 || i == 5 || i == 8) {
                expected = -1;
            }

            int result1 = lus.findLUSlength1(a, b);
            int result2 = lus.findLUSlength2(a, b);
            int result3 = lus.findLUSlength3(a, b);
            int result4 = lus.findLUSlength4(a, b);
            int result5 = lus.findLUSlength5(a, b);

            System.out.println("Test Case " + (i + 1) + ": a = " + a + ", b = " + b);
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
