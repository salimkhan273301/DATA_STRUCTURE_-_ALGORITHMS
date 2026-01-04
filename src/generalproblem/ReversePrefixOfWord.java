package generalproblem;

import java.util.Stack;

public class ReversePrefixOfWord {

    // Approach 1: Using StringBuilder and indexOf
    // Time Complexity: O(n), Space Complexity: O(n)
    public String reversePrefix1(String word, char ch) {
        int index = word.indexOf(ch);
        if (index == -1) return word;
        StringBuilder sb = new StringBuilder(word.substring(0, index + 1));
        return sb.reverse().toString() + word.substring(index + 1);
    }

    // Approach 2: Using Two Pointers
    // Time Complexity: O(n), Space Complexity: O(n)
    public String reversePrefix2(String word, char ch) {
        char[] chars = word.toCharArray();
        int left = 0;
        int right = word.indexOf(ch);
        if (right == -1) return word;
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
        return new String(chars);
    }

    // Approach 3: Using Stack
    // Time Complexity: O(n), Space Complexity: O(n)
    public String reversePrefix3(String word, char ch) {
        Stack<Character> stack = new Stack<>();
        int index = 0;
        while (index < word.length() && word.charAt(index) != ch) {
            stack.push(word.charAt(index));
            index++;
        }
        if (index == word.length()) return word;
        stack.push(word.charAt(index));
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString() + word.substring(index + 1);
    }

    // Approach 4: Using Recursion
    // Time Complexity: O(n), Space Complexity: O(n) (due to recursion stack)
    public String reversePrefix4(String word, char ch) {
        return reverseHelper(word, ch, 0);
    }

    private String reverseHelper(String word, char ch, int index) {
        if (index >= word.length() || word.charAt(index) == ch) {
            if (index >= word.length()) return word;
            return new StringBuilder(word.substring(0, index + 1)).reverse().toString() + word.substring(index + 1);
        }
        return reverseHelper(word, ch, index + 1);
    }

    // Approach 5: Using Java Streams (for demonstration, not recommended for production)
    // Time Complexity: O(n), Space Complexity: O(n)
    public String reversePrefix5(String word, char ch) {
        int index = word.indexOf(ch);
        if (index == -1) return word;
        String prefix = word.substring(0, index + 1);
        String reversedPrefix = new StringBuilder(prefix).reverse().toString();
        return reversedPrefix + word.substring(index + 1);
    }

    // Test Cases
    public static void main(String[] args) {
        ReversePrefixOfWord rpw = new ReversePrefixOfWord();
        String[] words = {"abcdefd", "xyxzxe", "abcd", "hello", "abcde"};
        char[] chs = {'d', 'z', 'a', 'l', 'f'};
        String[] expected = {"dcbaefd", "zxyxxe", "abcd", "lehlo", "abcde"};

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            char ch = chs[i];
            String exp = expected[i];
            
            String result1 = rpw.reversePrefix1(word, ch);
            String result2 = rpw.reversePrefix2(word, ch);
            String result3 = rpw.reversePrefix3(word, ch);
            String result4 = rpw.reversePrefix4(word, ch);
            String result5 = rpw.reversePrefix5(word, ch);

            System.out.println("Test Case " + (i + 1) + ": word = " + word + ", ch = " + ch);
            System.out.println("Expected: " + exp);
            System.out.println("Approach 1: " + result1 + " " + (result1.equals(exp) ? "✓" : "✗"));
            System.out.println("Approach 2: " + result2 + " " + (result2.equals(exp) ? "✓" : "✗"));
            System.out.println("Approach 3: " + result3 + " " + (result3.equals(exp) ? "✓" : "✗"));
            System.out.println("Approach 4: " + result4 + " " + (result4.equals(exp) ? "✓" : "✗"));
            System.out.println("Approach 5: " + result5 + " " + (result5.equals(exp) ? "✓" : "✗"));
            System.out.println();
        }
    }
}