package generalproblem;

import java.util.*;

public class RemoveAdjacentDuplicatesRecursively {

    // ---------- Solution 1: Recursive run-removal ----------
    public static String removeDuplicates1(String s) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            int j = i;
            while (j + 1 < s.length() && s.charAt(i) == s.charAt(j + 1)) j++;
            if (i == j) sb.append(s.charAt(i));
            i = j + 1;
        }
        String res = sb.toString();
        return res.equals(s) ? res : removeDuplicates1(res);
    }

    // ---------- Solution 2: Stack (Deque) ----------
    public static String removeDuplicates2(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek() == c) stack.pop();
            else stack.push(c);
        }
        StringBuilder sb = new StringBuilder();
        for (char c : stack) sb.insert(0, c);
        return sb.toString();
    }

    // ---------- Solution 3: Recursive index traversal ----------
    private static String helper3(String s, int i) {
        if (i == s.length()) return "";
        if (i + 1 < s.length() && s.charAt(i) == s.charAt(i + 1)) {
            char dup = s.charAt(i);
            while (i < s.length() && s.charAt(i) == dup) i++;
            return helper3(s, i);
        } else {
            String rest = helper3(s, i + 1);
            if (!rest.isEmpty() && rest.charAt(0) == s.charAt(i)) {
                return rest.substring(1);
            }
            return s.charAt(i) + rest;
        }
    }
    public static String removeDuplicates3(String s) {
        return helper3(s, 0);
    }

    // ---------- Solution 4: Two-pointer in-place ----------
    public static String removeDuplicates4(String s) {
        char[] arr = s.toCharArray();
        int i = 0;
        for (int j = 0; j < arr.length; j++) {
            if (i > 0 && arr[i - 1] == arr[j]) i--;
            else arr[i++] = arr[j];
        }
        return new String(arr, 0, i);
    }

    // ---------- Solution 5: StringBuilder as stack ----------
    public static String removeDuplicates5(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            int n = sb.length();
            if (n > 0 && sb.charAt(n - 1) == c) sb.deleteCharAt(n - 1);
            else sb.append(c);
        }
        return sb.toString();
    }

    // ---------- Solution 6: Regex replacement ----------
    public static String removeDuplicates6(String s) {
        String prev;
        do {
            prev = s;
            s = s.replaceAll("(.)\\1+", "");
        } while (!s.equals(prev));
        return s;
    }

    // ---------- Solution 7: Divide & Conquer ----------
    private static String merge7(String left, String right) {
        if (!left.isEmpty() && !right.isEmpty() && left.charAt(left.length()-1) == right.charAt(0)) {
            char dup = left.charAt(left.length()-1);
            int i = left.length()-1;
            while (i >= 0 && left.charAt(i) == dup) i--;
            int j = 0;
            while (j < right.length() && right.charAt(j) == dup) j++;
            return merge7(left.substring(0, i+1), right.substring(j));
        }
        return left + right;
    }
    public static String removeDuplicates7(String s) {
        if (s.length() <= 1) return s;
        int mid = s.length()/2;
        String left = removeDuplicates7(s.substring(0, mid));
        String right = removeDuplicates7(s.substring(mid));
        return merge7(left, right);
    }

    // ---------- Solution 8: Linked List simulation ----------
    static class Node {
        char val;
        Node next;
        Node(char v) { val = v; }
    }
    public static String removeDuplicates8(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek() == c) stack.pop();
            else stack.push(c);
        }
        StringBuilder sb = new StringBuilder();
        for (char c : stack) sb.insert(0, c);
        return sb.toString();
    }

    // ---------- Solution 9: Tail-recursive with accumulator ----------
    private static String helper9(String s, int i, StringBuilder acc) {
        if (i == s.length()) return acc.toString();
        if (acc.length() > 0 && acc.charAt(acc.length()-1) == s.charAt(i)) {
            acc.deleteCharAt(acc.length()-1);
        } else {
            acc.append(s.charAt(i));
        }
        return helper9(s, i+1, acc);
    }
    public static String removeDuplicates9(String s) {
        return helper9(s, 0, new StringBuilder());
    }

    // ---------- Solution 10: Iterative until stable ----------
    public static String removeDuplicates10(String s) {
        boolean changed;
        do {
            changed = false;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length();) {
                int j = i;
                while (j+1 < s.length() && s.charAt(i) == s.charAt(j+1)) j++;
                if (i == j) sb.append(s.charAt(i));
                else changed = true;
                i = j+1;
            }
            s = sb.toString();
        } while (changed);
        return s;
    }

    // ---------- Test Cases ----------
    public static void main(String[] args) {
        String[] tests = {
            "geeksforgeek",   // gksforgk
            "abccbccba",      // ""
            "abcd",           // abcd
            "cymueammmadhcwpvpf" // cymuedhcwpvpf
        };

        for (String t : tests) {
            System.out.println("Input: " + t);
            System.out.println("Sol1: " + removeDuplicates1(t));
            System.out.println("Sol2: " + removeDuplicates2(t));
            System.out.println("Sol3: " + removeDuplicates3(t));
            System.out.println("Sol4: " + removeDuplicates4(t));
            System.out.println("Sol5: " + removeDuplicates5(t));
            System.out.println("Sol6: " + removeDuplicates6(t));
            System.out.println("Sol7: " + removeDuplicates7(t));
            System.out.println("Sol8: " + removeDuplicates8(t));
            System.out.println("Sol9: " + removeDuplicates9(t));
            System.out.println("Sol10: " + removeDuplicates10(t));
            System.out.println("----------------------------");
        }
    }
}

