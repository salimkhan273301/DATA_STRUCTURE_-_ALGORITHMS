package generalproblem;

import java.util.*;

public class IsBalanced {

    // 1. Stack-based standard solution
    static boolean isBalanced1(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') stack.push(c);
            else {
                if (stack.isEmpty()) return false;
                char top = stack.pop();
                if ((c == ')' && top != '(') ||
                    (c == '}' && top != '{') ||
                    (c == ']' && top != '[')) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
    
    
    public boolean isValid(String s) {
        while(s.contains("[]") || s.contains("()")|| s.contains ("{}")){
            s=s.replaceAll("\\(\\)","");
            s=s.replaceAll("\\[\\]","");
            s=s.replaceAll("\\{\\}","");
            
        }
        return s.isEmpty();
    }
    

    // 2. Replace pairs until empty (your idea, corrected)
    static boolean isBalanced2(String s) {
        int len;
        do {
            len = s.length();
            s = s.replace("()", "").replace("{}", "").replace("[]", "");
        } while (s.length() != len);
        return s.isEmpty();
    }

    // 3. Recursive reduction
    static boolean isBalanced3(String s) {
        if (s.isEmpty()) return true;
        String reduced = s.replace("()", "").replace("{}", "").replace("[]", "");
        if (reduced.equals(s)) return false; // no progress
        return isBalanced3(reduced);
    }

    // 4. Using Map and Stack
    static boolean isBalanced4(String s) {
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (map.containsValue(c)) stack.push(c);
            else if (map.containsKey(c)) {
                if (stack.isEmpty() || stack.pop() != map.get(c)) return false;
            }
        }
        return stack.isEmpty();
    }

    // 5. Using Deque instead of Stack
    static boolean isBalanced5(String s) {
        Deque<Character> dq = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if ("({[".indexOf(c) != -1) dq.push(c);
            else {
                if (dq.isEmpty()) return false;
                char top = dq.pop();
                if ((c == ')' && top != '(') ||
                    (c == '}' && top != '{') ||
                    (c == ']' && top != '[')) {
                    return false;
                }
            }
        }
        return dq.isEmpty();
    }

    // 6. Count-based approach (only for parentheses `()` case)
    static boolean isBalanced6(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') count++;
            else if (c == ')') count--;
            if (count < 0) return false;
        }
        return count == 0;
    }

    // 7. Two-pass with regex for speed
    static boolean isBalanced7(String s) {
        String regex = "\\(\\)|\\{}|\\[]";
        int len;
        do {
            len = s.length();
            s = s.replaceAll("\\(\\)|\\{}|\\[]", "");
        } while (s.length() != len);
        return s.isEmpty();
    }

    // 8. Using LinkedList as stack
    static boolean isBalanced8(String s) {
        LinkedList<Character> list = new LinkedList<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') list.addLast(c);
            else {
                if (list.isEmpty()) return false;
                char top = list.removeLast();
                if ((c == ')' && top != '(') ||
                    (c == '}' && top != '{') ||
                    (c == ']' && top != '[')) {
                    return false;
                }
            }
        }
        return list.isEmpty();
    }

    // 9. Balanced check using char codes trick
    static boolean isBalanced9(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') stack.push(c);
            else {
                if (stack.isEmpty()) return false;
                char top = stack.pop();
                // ASCII difference: ()=1, []=2, {}=2
                if (Math.abs(c - top) > 2) return false;
            }
        }
        return stack.isEmpty();
    }

    // 10. Divide and conquer
    static boolean isBalanced10(String s) {
        return checkBalanced(s, 0, s.length() - 1) != -1;
    }

    private static int checkBalanced(String s, int start, int end) {
        Stack<Character> stack = new Stack<>();
        for (int i = start; i <= end; i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') stack.push(c);
            else {
                if (stack.isEmpty()) return -1;
                char top = stack.pop();
                if ((c == ')' && top != '(') ||
                    (c == '}' && top != '{') ||
                    (c == ']' && top != '[')) return -1;
            }
        }
        return stack.isEmpty() ? 1 : -1;
    }

    // Quick test
    public static void main(String[] args) {
        String[] tests = {"()", "({[]})", "(]", "({)}", "([]{})", "((("};

        for (String t : tests) {
            System.out.println("Testing: " + t);
            System.out.println("isBalanced1: " + isBalanced1(t));
            System.out.println("isBalanced2: " + isBalanced2(t));
            System.out.println("isBalanced3: " + isBalanced3(t));
            System.out.println("isBalanced4: " + isBalanced4(t));
            System.out.println("isBalanced5: " + isBalanced5(t));
            System.out.println("isBalanced6: " + isBalanced6(t));
            System.out.println("isBalanced7: " + isBalanced7(t));
            System.out.println("isBalanced8: " + isBalanced8(t));
            System.out.println("isBalanced9: " + isBalanced9(t));
            System.out.println("isBalanced10: " + isBalanced10(t));
            System.out.println("----");
        }
    }
}
