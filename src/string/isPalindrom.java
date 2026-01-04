package string;

public class isPalindrom {

    // 1️⃣ Two-pointer (iterative, skipping non-alphanumeric on the fly)
    public static boolean isPalindrome1(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            while (i < j && !Character.isLetterOrDigit(s.charAt(i))) i++;
            while (i < j && !Character.isLetterOrDigit(s.charAt(j))) j--;
            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
                return false;
            }
            i++; j--;
        }
        return true;
    }

    // 2️⃣ Clean string + reverse string comparison
    public static boolean isPalindrome2(String s) {
        String cleaned = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        String reversed = new StringBuilder(cleaned).reverse().toString();
        return cleaned.equals(reversed);
    }

    // 3️⃣ Clean string + two-pointer comparison
    public static boolean isPalindrome3(String s) {
        String cleaned = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        int i = 0, j = cleaned.length() - 1;
        while (i < j) {
            if (cleaned.charAt(i++) != cleaned.charAt(j--)) return false;
        }
        return true;
    }

    // 4️⃣ Using recursion
    public static boolean isPalindrome4(String s) {
        String cleaned = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        return helper(cleaned, 0, cleaned.length() - 1);
    }
    private static boolean helper(String s, int i, int j) {
        if (i >= j) return true;
        if (s.charAt(i) != s.charAt(j)) return false;
        return helper(s, i+1, j-1);
    }

    // 5️⃣ Using Stack
    public static boolean isPalindrome5(String s) {
        String cleaned = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        java.util.Stack<Character> stack = new java.util.Stack<>();
        for (int i = 0; i < cleaned.length(); i++) stack.push(cleaned.charAt(i));
        for (int i = 0; i < cleaned.length(); i++) {
            if (cleaned.charAt(i) != stack.pop()) return false;
        }
        return true;
    }

    // 6️⃣ Using Deque
    public static boolean isPalindrome6(String s) {
        java.util.Deque<Character> dq = new java.util.ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (Character.isLetterOrDigit(c)) dq.add(Character.toLowerCase(c));
        }
        while (dq.size() > 1) {
            if (dq.pollFirst() != dq.pollLast()) return false;
        }
        return true;
    }

    // 7️⃣ Using StringBuilder filtering manually
    public static boolean isPalindrome7(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isLetterOrDigit(c)) sb.append(Character.toLowerCase(c));
        }
        String cleaned = sb.toString();
        return cleaned.equals(sb.reverse().toString());
    }

    // 8️⃣ Using regex + char array two-pointer
    public static boolean isPalindrome8(String s) {
        char[] arr = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase().toCharArray();
        int i = 0, j = arr.length - 1;
        while (i < j) {
            if (arr[i++] != arr[j--]) return false;
        }
        return true;
    }

    // 9️⃣ Using streams (Java 8+)
    public static boolean isPalindrome9(String s) {
        String cleaned = s.chars()
                .filter(Character::isLetterOrDigit)
                .mapToObj(c -> String.valueOf((char) c).toLowerCase())
                .reduce("", String::concat);
        return cleaned.equals(new StringBuilder(cleaned).reverse().toString());
    }

    // 🔟 Using half-scan (compare only half, no full reverse)
    public static boolean isPalindrome10(String s) {
        String cleaned = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        for (int i = 0; i < cleaned.length() / 2; i++) {
            if (cleaned.charAt(i) != cleaned.charAt(cleaned.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    // ------------------ TEST CASES ------------------
    public static void main(String[] args) {
        String[] tests = {
            "A man, a plan, a canal: Panama", // true
            "race a car",                     // false
            " ",                              // true (empty after cleaning)
            "0P",                             // false (case sensitive check)
            "abba",                           // true
            "abcba",                          // true
            "abcd",                           // false
            "No lemon, no melon",             // true
            "Was it a car or a cat I saw?",   // true
            "Madam In Eden, I’m Adam"         // true
        };

        for (String t : tests) {
            System.out.println("Input: \"" + t + "\"");
            System.out.println("Sol1: " + isPalindrome1(t));
            System.out.println("Sol2: " + isPalindrome2(t));
            System.out.println("Sol3: " + isPalindrome3(t));
            System.out.println("Sol4: " + isPalindrome4(t));
            System.out.println("Sol5: " + isPalindrome5(t));
            System.out.println("Sol6: " + isPalindrome6(t));
            System.out.println("Sol7: " + isPalindrome7(t));
            System.out.println("Sol8: " + isPalindrome8(t));
            System.out.println("Sol9: " + isPalindrome9(t));
            System.out.println("Sol10: " + isPalindrome10(t));
            System.out.println("----------------------------");
        }
    }
}
