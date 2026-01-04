package generalproblem;

public class IsPalindrom {

    // 1. Two Pointers Approach (Iterative)
    boolean isPalindrome1(String s) {
        int start = 0, end = s.length() - 1;
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    // 2. Reverse String and Compare
    boolean isPalindrome2(String s) {
        String reversed = new StringBuilder(s).reverse().toString();
        return s.equals(reversed);
    }

    // 3. Recursion
    boolean isPalindrome3(String s) {
        return helper(s, 0, s.length() - 1);
    }
    private boolean helper(String s, int left, int right) {
        if (left >= right) return true;  // Base case
        if (s.charAt(left) != s.charAt(right)) return false;
        return helper(s, left + 1, right - 1);
    }

    // 4. Using Stack
    boolean isPalindrome4(String s) {
        java.util.Stack<Character> stack = new java.util.Stack<>();
        for (char c : s.toCharArray()) {
            stack.push(c);
        }
        for (char c : s.toCharArray()) {
            if (c != stack.pop()) return false;
        }
        return true;
    }

    // 5. Half String Compare
    boolean isPalindrome5(String s) {
        int n = s.length();
        for (int i = 0; i < n / 2; i++) {
            if (s.charAt(i) != s.charAt(n - i - 1)) {
                return false;
            }
        }
        return true;
    }

    // Quick test
    public static void main(String[] args) {
    	IsPalindrom sol = new IsPalindrom();
        String[] tests = {"madam", "racecar", "hello", "abba", "abc"};

        for (String t : tests) {
            System.out.println("Testing: " + t);
            System.out.println("isPalindrome1: " + sol.isPalindrome1(t));
            System.out.println("isPalindrome2: " + sol.isPalindrome2(t));
            System.out.println("isPalindrome3: " + sol.isPalindrome3(t));
            System.out.println("isPalindrome4: " + sol.isPalindrome4(t));
            System.out.println("isPalindrome5: " + sol.isPalindrome5(t));
            System.out.println("----");
        }
    }
}

