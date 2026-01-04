package backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SubsetBacktracking {

    // Method 1: Basic Backtracking (Include/Exclude elements)
    public static List<List<Integer>> generatePowerSetBasic(ArrayList<Integer> arr) {
        List<List<Integer>> powerSet = new ArrayList<>();
        backtrackBasic(arr, 0, new ArrayList<>(), powerSet);
        return powerSet;
    }

    private static void backtrackBasic(ArrayList<Integer> arr, int start, List<Integer> current, List<List<Integer>> powerSet) {
        if (start == arr.size()) {
            powerSet.add(new ArrayList<>(current));
            return;
        }
        // Include the current element
        current.add(arr.get(start));
        backtrackBasic(arr, start + 1, current, powerSet);
        // Exclude the current element (backtrack)
        current.remove(current.size() - 1);
        backtrackBasic(arr, start + 1, current, powerSet);
    }

    // Method 2: Lexicographic Order (Alternative Backtracking)
    public static List<List<Integer>> generatePowerSetLex(ArrayList<Integer> arr) {
        List<List<Integer>> powerSet = new ArrayList<>();
        backtrackLex(arr, 0, new ArrayList<>(), powerSet);
        return powerSet;
    }

    private static void backtrackLex(ArrayList<Integer> arr, int index, List<Integer> current, List<List<Integer>> powerSet) {
        powerSet.add(new ArrayList<>(current));
        for (int i = index; i < arr.size(); i++) {
            current.add(arr.get(i));
            backtrackLex(arr, i + 1, current, powerSet);
            current.remove(current.size() - 1);
        }
    }

    // Method 3: Iterative Backtracking (Using Stack)
    public static List<List<Integer>> generatePowerSetIterative(ArrayList<Integer> arr) {
        List<List<Integer>> powerSet = new ArrayList<>();
        Stack<List<Integer>> stack = new Stack<>();
        stack.push(new ArrayList<>());
        
        while (!stack.isEmpty()) {
            List<Integer> current = stack.pop();
            powerSet.add(current);
            for (int i = arr.size() - 1; i >= 0; i--) {
                if (current.isEmpty() || arr.get(i) > current.get(current.size() - 1)) {
                    List<Integer> newSubset = new ArrayList<>(current);
                    newSubset.add(arr.get(i));
                    stack.push(newSubset);
                }
            }
        }
        return powerSet;
    }

    // Main method to test all approaches
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(2);
        arr.add(3);

        System.out.println("Basic Backtracking:");
        System.out.println(generatePowerSetBasic(arr));

        System.out.println("\nLexicographic Order Backtracking:");
        System.out.println(generatePowerSetLex(arr));

        System.out.println("\nIterative Backtracking:");
        System.out.println(generatePowerSetIterative(arr));
    }
}
